package com.quizmongoservice.serviceimpl;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.repository.QuizRepository;
import com.quizmongoservice.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizServiceImpl implements QuizService {

    final QuizRepository quizRepository;
    final MongoTemplate mongoTemplate;

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        System.out.println("DATABASE NAME = " + mongoTemplate.getDb().getName());
        return quizRepository.save(quiz);
    }





}
