package com.example.mapping_practise.dto.responseDTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {

    private Long Id;
    private String name;
    private List<String> bookNames;
}
