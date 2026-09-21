package com.questionsgenerator.service;

import com.questionsgenerator.dto.QuizDto;

public interface QuizWithFeignClientService {

    QuizDto getQuizById(String quizId);
    QuizDto saveQuiz(QuizDto quiz);

}
