package com.quizmongoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@RefreshScope
public class Test {


    @Value("${config.value}")
    public String config;

    @GetMapping
    public String get(){
    return config;
    }



}
