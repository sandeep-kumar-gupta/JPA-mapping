package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.CategoryRequestDto;
import com.example.mapping_practise.dto.responseDTO.CategoryResponseDto;
import com.example.mapping_practise.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category getCategory(Long categoryId);
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto getCategoryById(Long categoryId);

    List<CategoryResponseDto> getCategories();
    CategoryResponseDto deleteCategory(Long categoryId);
    CategoryResponseDto editCategory(Long categoryId,CategoryRequestDto categoryRequestDto);


}
