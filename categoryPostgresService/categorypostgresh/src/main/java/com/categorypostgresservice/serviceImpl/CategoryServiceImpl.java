package com.categorypostgresservice.serviceImpl;

import com.categorypostgresservice.dto.CategoryDto;
import com.categorypostgresservice.entities.Category;
import com.categorypostgresservice.repository.CategoryRepository;
import com.categorypostgresservice.services.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {

   final CategoryRepository categoryRepo;
   final ModelMapper mapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category entity = mapper.map(categoryDto, Category.class);
        Category save = categoryRepo.save(entity);
        CategoryDto dto = mapper.map(save, CategoryDto.class);
        return dto;
    }

    @Override
    public CategoryDto findByCategoryId(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
        CategoryDto dto = mapper.map(category, CategoryDto.class);
        return dto;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
        Category entity = mapper.map(categoryDto, Category.class);
        entity.setId(categoryId);
        Category entitysave = categoryRepo.save(entity);
        CategoryDto dto = mapper.map(entitysave, CategoryDto.class);
        return dto;
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<CategoryDto> collect = categoryRepo.findAll().stream().map(c -> mapper.map(c, CategoryDto.class)).collect(Collectors.toList());
        return collect;
    }

}
