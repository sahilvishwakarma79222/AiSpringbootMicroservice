package com.questionsgenerator.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class QuizService {


    @Bean
    public Consumer<QuizDto> getQuizBinding(){
        return quizDto -> {
            System.out.println("Quiz Created Event Received!!!!");
            System.out.println(quizDto.getTitle());
        };
    }
}
