package com.questionsgenerator.controller;

import com.questionsgenerator.collections.Questions;
import com.questionsgenerator.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    @Autowired
    public QuestionRepo questionRepo;


    @GetMapping("/getAll")
    public ResponseEntity<List<Questions>> findAllQuestions(){
        List<Questions> all = questionRepo.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

}
