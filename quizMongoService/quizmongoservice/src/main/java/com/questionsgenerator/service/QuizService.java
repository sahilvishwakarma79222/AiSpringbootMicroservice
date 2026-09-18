package com.questionsgenerator.service;

import com.questionsgenerator.dto.QuizDto;

import java.util.List;

public interface QuizService {

    QuizDto saveQuiz(QuizDto quiz);
    List<QuizDto> findByCategoryId(String catid);
    List<QuizDto> findAllQuiz();
    QuizDto findByQuizId(String quidId);

}
