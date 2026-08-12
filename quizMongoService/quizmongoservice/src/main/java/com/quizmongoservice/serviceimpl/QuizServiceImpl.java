package com.quizmongoservice.serviceimpl;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.repository.QuizRepository;
import com.quizmongoservice.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizServiceImpl implements QuizService {

    final QuizRepository quizRepository;
    final MongoTemplate mongoTemplate;


    @Override
    public QuizDto saveQuiz(QuizDto quizDto) {

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