package com.quizmongoservice.controller;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizController {

    private final QuizService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveQuiz(@RequestBody Quiz quiz){
        Quiz quiz1 = service.saveQuiz(quiz);
        return new ResponseEntity<>(quiz1, HttpStatus.OK);
    }

}
