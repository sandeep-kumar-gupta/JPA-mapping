package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.AuthorRequestDto;
import com.example.mapping_practise.dto.responseDTO.AuthorResponseDto;
import com.example.mapping_practise.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {

    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    List<AuthorResponseDto> getAuthors();
    AuthorResponseDto getAuthorById(Long authorId);

    Author getAuthor(Long authorId);

    AuthorResponseDto deleteAuthor(Long authorId);

    AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);

    AuthorResponseDto addZipcodeToauthor(Long authorId,Long zipCodeId);

    AuthorResponseDto deleteZipcodeToauthor(Long authorId);


}
