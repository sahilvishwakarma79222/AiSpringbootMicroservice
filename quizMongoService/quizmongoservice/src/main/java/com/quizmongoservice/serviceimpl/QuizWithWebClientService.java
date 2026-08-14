package com.quizmongoservice.serviceimpl;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.dto.CategoryDto;
import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.repository.QuizRepository;
import com.quizmongoservice.service.QuizWithWebClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizWithWebClientService implements QuizWithWebClient {

    final QuizRepository quizRepository;
    final MongoTemplate mongoTemplate;
    final WebClient webClient;
    private static final String CATEGORY_BASE_URL = "http://192.168.1.220:9091/api/v1/category";

    public QuizWithWebClientService(QuizRepository quizRepository,MongoTemplate mongoTemplate
    ,WebClient.Builder builder){
        this.quizRepository=quizRepository;
        this.mongoTemplate=mongoTemplate;
        this.webClient=builder.baseUrl("http://192.168.1.220:9091").build();
    }

    @Override
    public QuizDto saveQuiz(QuizDto quizDto) {
        String catId = quizDto.getCategoryId();
        try{
            CategoryDto catDto = webClient.get().uri("/api/v1/category/get/{catId}", catId)
                    .retrieve()
                    .bodyToMono(CategoryDto.class)
                    .block();
        }catch (Exception e){
            throw new RuntimeException("Category Not Found With Id "+catId);
        }

        Quiz quiz = toEntity(quizDto);

        Quiz savedEntity = quizRepository.save(quiz);

        return toDto(savedEntity);
    }



    // ==================== CONVERSION METHODS ====================

    private QuizDto toDto(Quiz quiz) {

        QuizDto dto = new QuizDto();

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
