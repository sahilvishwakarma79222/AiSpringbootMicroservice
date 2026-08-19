package com.quizmongoservice.serviceimpl;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.dto.CategoryClient;
import com.quizmongoservice.dto.CategoryDto;
import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.repository.QuizRepository;
import com.quizmongoservice.service.QuizWithFeignClientService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class QuizWithFeignClientServiceImpl implements QuizWithFeignClientService {

    final CategoryClient categoryFeignClient;
    final QuizRepository quizRepository;

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
        return toDto(savedEntity);
    }

    @Override
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
