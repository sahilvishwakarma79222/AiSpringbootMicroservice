package com.quizmongoservice.controller;

import com.quizmongoservice.collection.Quiz;
import com.quizmongoservice.dto.QuizDto;
import com.quizmongoservice.service.QuizService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizController {

    private final QuizService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveQuiz(@RequestBody QuizDto quiz){
        QuizDto quiz1 = service.saveQuiz(quiz);
        return new ResponseEntity<>(quiz1, HttpStatus.OK);
    }

    @GetMapping("/getByCategoryid/{categoryId}")
    public ResponseEntity<?> findQuizByCategoryId(@PathVariable String categoryId){
        List<QuizDto> response=service.findByCategoryId(categoryId);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getByQuizId/{quizId}")
    public ResponseEntity<?> findQuizByQuizId(@PathVariable String quizId){
        QuizDto response=service.findByQuizId(quizId);
            System.out.println(response);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/findAllQuiz")
    public ResponseEntity<?> findAllQuiz(){
        List<QuizDto> response=service.findAllQuiz();
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


}
