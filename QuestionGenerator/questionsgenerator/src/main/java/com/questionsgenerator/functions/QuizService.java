package com.questionsgenerator.functions;

import com.questionsgenerator.service.QuestionGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class QuizService {


    @Autowired
    private QuestionGeneratorService service;

//    @Bean
//    public Consumer<QuizDto> getQuizBinding(){
//        return quizDto -> {
//            System.out.println("Quiz Created Event Received!!!!");
//            System.out.println(quizDto.getTitle());
//        };
//    }

    @Bean
    public Function<QuizDto,String> getQuizBinding(){

        return information->{
            System.out.println(information.getTitle());
            System.out.println(information.getCategoryId());
            this.service.generateAndSaveQuestions(information);
            return "Quiz Created succesfully!!!";
        };
    }

//    @Bean
//    public Function<QuizDto,String> getQuizBinding(){
//
//        return information->{
//            System.out.println(information.getTitle());
//            System.out.println(information.getCategoryId());
//            return "Quiz Created succesfully!!!";
//        };
//    }
}
