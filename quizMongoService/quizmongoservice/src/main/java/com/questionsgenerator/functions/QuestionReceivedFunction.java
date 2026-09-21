package com.questionsgenerator.functions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class QuestionReceivedFunction {


    @Bean
    public Consumer<String> questionsCreatedBinding(){
        return information->{
            System.out.println("yes question ne question generate kr diya hai !!!");

        };
    }

}
