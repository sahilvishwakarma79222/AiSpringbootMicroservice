package com.quizmongoservice.service;

import com.quizmongoservice.dto.QuizDto;

public interface QuizWithFeignClientService {

    QuizDto getQuizById(String quizId);
    QuizDto saveQuiz(QuizDto quiz);

}
