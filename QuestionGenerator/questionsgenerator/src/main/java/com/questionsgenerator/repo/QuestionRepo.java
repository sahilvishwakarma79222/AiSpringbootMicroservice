package com.questionsgenerator.repo;

import com.questionsgenerator.collections.Questions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepo extends MongoRepository<Questions,String> {

}
