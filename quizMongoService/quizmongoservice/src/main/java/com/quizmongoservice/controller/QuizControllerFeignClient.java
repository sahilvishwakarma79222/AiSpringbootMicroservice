package com.quizmongoservice.controller;

import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.service.QuizWithFeignClientService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quiz/feign")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class QuizControllerFeignClient {

    QuizWithFeignClientService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveQuiz(@RequestBody QuizDto quiz){
        QuizDto quiz1 = service.saveQuiz(quiz);
        return new ResponseEntity<>(quiz1, HttpStatus.OK);
    }


}
