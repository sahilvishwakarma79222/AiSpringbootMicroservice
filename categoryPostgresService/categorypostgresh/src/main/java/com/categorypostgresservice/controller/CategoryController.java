package com.categorypostgresservice.controller;

import com.categorypostgresservice.dto.CategoryDto;
import com.categorypostgresservice.services.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CategoryController {

private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.saveCategory(categoryDto), HttpStatus.OK);
    }

    @GetMapping("/get/{catId}")
    public ResponseEntity<?> findCategory(@PathVariable Long catId){
        return new ResponseEntity<>(categoryService.findByCategoryId(catId), HttpStatus.OK);
    }

    @PutMapping("/update/{catId}")
    public ResponseEntity<?> saveCategory(@PathVariable Long catId,@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.updateCategory(catId,categoryDto),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllCategory(){
        return new ResponseEntity<>(categoryService.findAllCategory(), HttpStatus.OK);
    }

}
