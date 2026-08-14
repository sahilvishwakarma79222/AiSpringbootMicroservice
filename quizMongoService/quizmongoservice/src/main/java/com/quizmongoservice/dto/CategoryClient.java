package com.quizmongoservice.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "category-service",
        url = "http://192.168.1.220:9091"
)
public interface CategoryClient {

    @GetMapping("/api/v1/category/get/{catId}")
    CategoryDto getCategory(@PathVariable("catId") String catId);

}
