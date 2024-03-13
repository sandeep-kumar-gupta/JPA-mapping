package com.example.mapping_practise.dto.responseDTO;

import lombok.Data;

import java.util.List;
@Data
public class AuthorResponseDto {

    private Long id;
    private String Name;

    private List<String> bookName;

    private String zipCodeName;

}
