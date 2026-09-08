package com.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class FallBackController {

    @GetMapping("/categoryfallback")
    public Mono<String> categoryServiceFallBack(){
        return Mono.just("Category service is unavailabe right now Please try again later !!!");
    }



}
