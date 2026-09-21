package com.questionsgenerator.service;

import com.questionsgenerator.collections.Questions;
import com.questionsgenerator.functions.QuizDto;

import java.util.List;

public interface QuestionGeneratorService {
    List<Questions>generateQuestion(String quizName, int  numberOfQuestion, String  description);

    void generateAndSaveQuestions(QuizDto quizDto);
}
