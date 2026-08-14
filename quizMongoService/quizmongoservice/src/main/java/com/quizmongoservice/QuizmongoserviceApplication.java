package com.quizmongoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuizmongoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizmongoserviceApplication.class, args);
	}

}
