package com.quizmongoservice.service;

import com.quizmongoservice.dto.QuizDto;

public interface QuizWithFeignClientService {

    QuizDto saveQuiz(QuizDto quiz);

}
