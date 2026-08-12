package com.quizmongoservice.repository;

import com.quizmongoservice.collection.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz,String> {

    List<Quiz> findByTitle(String title);

}
