package com.example.mapping_practise.controller;

import com.example.mapping_practise.dto.requestDTO.AuthorRequestDto;
import com.example.mapping_practise.dto.responseDTO.AuthorResponseDto;
import com.example.mapping_practise.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/addAuthor")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable final Long id){
        AuthorResponseDto authorResponseDto = authorService.getAuthorById(id);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }
    @GetMapping("/getAuthors")
    public ResponseEntity<List<AuthorResponseDto>> getAuthors(){
        List<AuthorResponseDto> authorResponseDtos = authorService.getAuthors();
        return  new ResponseEntity<>(authorResponseDtos,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable final Long id){
        AuthorResponseDto authorResponseDto = authorService.deleteAuthor(id);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<AuthorResponseDto> editAuthor(@PathVariable final Long id,@RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.editAuthor(id,authorRequestDto);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }
    @PostMapping("/addZipcode/{zipcodeId}/to/{authorId}")
    public ResponseEntity<AuthorResponseDto> addZipCode(@PathVariable final Long zipcodeId,
                                                        @PathVariable final long authorId){
        AuthorResponseDto authorResponseDto = authorService.addZipcodeToauthor(authorId, zipcodeId);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }
    @PostMapping("/removeZipCode/{id}")
    public ResponseEntity<AuthorResponseDto> addZipCode(@PathVariable final Long id){

        AuthorResponseDto authorResponseDto = authorService.deleteZipcodeToauthor(id);
        return  new ResponseEntity<>(authorResponseDto,HttpStatus.OK);
    }




}
