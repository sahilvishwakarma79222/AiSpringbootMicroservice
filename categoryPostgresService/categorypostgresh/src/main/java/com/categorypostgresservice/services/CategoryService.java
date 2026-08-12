package com.categorypostgresservice.services;

import com.categorypostgresservice.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto findByCategoryId(Long categoryId);
    CategoryDto updateCategory(Long categoryId,CategoryDto categoryDto);
    List<CategoryDto> findAllCategory();

}
