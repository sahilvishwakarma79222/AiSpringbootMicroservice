package com.quizmongoservice.controller;

import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.serviceimpl.QuizWithWebClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz/webclient")
public class QuizControllerWebClient {

private final QuizWithWebClientService service;

    public QuizControllerWebClient(QuizWithWebClientService service){
    this.service=service;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveQuiz(@RequestBody QuizDto quiz){
        QuizDto quiz1 = service.saveQuiz(quiz);
        return new ResponseEntity<>(quiz1, HttpStatus.OK);
    }

}
