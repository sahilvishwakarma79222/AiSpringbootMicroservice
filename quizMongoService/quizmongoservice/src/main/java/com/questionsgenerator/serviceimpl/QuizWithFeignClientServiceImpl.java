package com.questionsgenerator.serviceimpl;

import com.questionsgenerator.collection.Quiz;
import com.questionsgenerator.dto.CategoryClient;
import com.questionsgenerator.dto.CategoryDto;
import com.questionsgenerator.dto.QuizDto;
import com.questionsgenerator.repository.QuizRepository;
import com.questionsgenerator.service.QuizWithFeignClientService;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class QuizWithFeignClientServiceImpl implements QuizWithFeignClientService {

    final CategoryClient categoryFeignClient;
    final QuizRepository quizRepository;
    final StreamBridge streamBridge;
    @Override
    public QuizDto saveQuiz(QuizDto quizDto) {
        String categoryId = quizDto.getCategoryId();
        CategoryDto categoryDto;

        try {
            categoryDto = categoryFeignClient.getCategory(categoryId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Category not found with id: " + categoryId);
        }        Quiz quiz = toEntity(quizDto);
        Quiz savedEntity = quizRepository.save(quiz);

        publicQuizCreatedEvent(quizDto);
        return toDto(savedEntity);
    }

    private void publicQuizCreatedEvent(QuizDto quizDto) {

        boolean send = this.streamBridge.send("quizCreatedBinding-out-0", quizDto);
        if(send) System.out.println("Succesfully send To the broker");
        else System.out.println("Something went wrong while send to broker (from quiz)");
    }

    @Override
    @CircuitBreaker(name = "quizCB", fallbackMethod = "quizFallback")
    public QuizDto getQuizById(String quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("QuizNotFoundWithId " + quizId));
        CategoryDto categoryDto=null;
        CategoryDto category = categoryFeignClient.getCategory(quiz.getCategoryId());
        if(category!=null){
            categoryDto=category;
        }
        QuizDto dto = toDto(quiz);
        dto.setCategoryDto(category);
        return dto;
    }

    public QuizDto quizFallback(String quizId, Throwable t) {
        System.out.println("Category not found");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Fallback category");
        return new QuizDto();
    }


    // ==================== CONVERSION METHODS ====================

    private QuizDto toDto(Quiz quiz) {

        QuizDto dto = new QuizDto();

        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setMaxMarks(quiz.getMaxMarks());
        dto.setTimeLimit(quiz.getTimeLimit());
        dto.setCreatedBy(quiz.getCreatedBy());
        dto.setNoOfQuestions(quiz.getNoOfQuestions());
        dto.setImageUrl(quiz.getImageUrl());
        dto.setLive(quiz.getLive());
        dto.setPassingMarks(quiz.getPassingMarks());
        dto.setCategoryId(quiz.getCategoryId());

        return dto;
    }


    private Quiz toEntity(QuizDto dto) {

        Quiz quiz = new Quiz();

        quiz.setTitle(dto.getTitle());
        quiz.setDescription(dto.getDescription());
        quiz.setMaxMarks(dto.getMaxMarks());
        quiz.setTimeLimit(dto.getTimeLimit());
        quiz.setCreatedBy(dto.getCreatedBy());
        quiz.setNoOfQuestions(dto.getNoOfQuestions());
        quiz.setImageUrl(dto.getImageUrl());
        quiz.setLive(dto.getLive());
        quiz.setPassingMarks(dto.getPassingMarks());
        quiz.setCategoryId(dto.getCategoryId());

        return quiz;
    }

}
