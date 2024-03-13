package com.example.mapping_practise.controller;

import com.example.mapping_practise.dto.requestDTO.CategoryRequestDto;
import com.example.mapping_practise.dto.responseDTO.CategoryResponseDto;
import com.example.mapping_practise.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody final CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable final Long id) {

        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryResponseDto>>getCategory() {

        List<CategoryResponseDto> categoryResponseDtos = categoryService.getCategories();
        return new ResponseEntity<>(categoryResponseDtos, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable final Long id) {

        CategoryResponseDto categoryResponseDto = categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> editCategory(@PathVariable final Long id,
                                                            @RequestBody final CategoryRequestDto categoryRequestDto) {

        CategoryResponseDto categoryResponseDto = categoryService.editCategory(id,categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);

    }


}
