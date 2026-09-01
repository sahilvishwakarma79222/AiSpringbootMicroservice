package com.categorypostgresservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class Test {

    @Value("${config.value}")
    private String config;

    @GetMapping
    public String getConfig(){
        return  config;
    }


}

