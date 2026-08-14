package com.quizmongoservice.serviceimpl;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.dto.CategoryDto;
import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.repository.QuizRepository;
import com.quizmongoservice.service.QuizWithRestTemplateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizWithRestTemplate implements QuizWithRestTemplateService {


        final QuizRepository quizRepository;
        final MongoTemplate mongoTemplate;
        final RestTemplate restTemplate;

        @Override
        public QuizDto saveQuiz(QuizDto quizDto) {
            String catId = quizDto.getCategoryId();
            try {

                CategoryDto categoryDto = restTemplate.getForObject(
                        "http://192.168.1.220:9091/api/v1/category/get/{catId}",
                        CategoryDto.class,
                        catId
                );

            } catch (HttpClientErrorException e) {

                throw new RuntimeException(
                        "Category not found with id: " + catId
                );
            }catch (HttpServerErrorException ex) {
                // handle 5xx
                throw new RuntimeException(
                        "Category not found with id: " + catId
                );
            }


            Quiz quiz = toEntity(quizDto);

        Quiz savedEntity = quizRepository.save(quiz);

        return toDto(savedEntity);
    }


        @Override
        public List<QuizDto> findByCategoryId(String catid) {

        return quizRepository.findByCategoryId(catid)
                .stream()
                .map(this::toDto)
                .toList();
    }


        @Override
        public List<QuizDto> findAllQuiz() {

        return quizRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }


        @Override
        public QuizDto findByQuizId(String quizId) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Quiz not found with id " + quizId
                        )
                );

        return toDto(quiz);
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

