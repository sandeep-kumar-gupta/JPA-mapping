package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.mapper;
import com.example.mapping_practise.dto.requestDTO.AuthorRequestDto;
import com.example.mapping_practise.dto.responseDTO.AuthorResponseDto;
import com.example.mapping_practise.model.Author;
import com.example.mapping_practise.model.ZipCode;
import com.example.mapping_practise.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipCodeService zipCodeService;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipCodeService zipCodeService) {
        this.authorRepository = authorRepository;
        this.zipCodeService = zipCodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipCodeId()==null){
            throw new IllegalArgumentException("author need zipcode");
        }
        ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
        author.setZipCode(zipCode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
       List<Author> authors = StreamSupport.stream(authorRepository.findAll().
               spliterator(),false) .collect(Collectors.toList());
        return mapper.authorToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {

        return mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {

        Author author = authorRepository.findById(authorId).orElseThrow(()->
                new IllegalArgumentException("author with id: "+authorId+"could not found"));
        return author;

    }
    @Transactional
    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {

        Author author =  getAuthor(authorId);
        authorRepository.delete(author);
        return mapper.authorToAuthorResponseDto(author);

    }

    @Transactionall
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {

        Author authorToEdit= getAuthor(authorId);
        authorToEdit.setName(authorRequestDto.getName());
        if(authorRequestDto.getZipCodeId() != null){
            ZipCode zipCode = zipCodeService.getZipCode(authorRequestDto.getZipCodeId());
        }
        return mapper.authorToAuthorResponseDto(authorToEdit);
    }
    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToauthor(Long authorId, Long zipCodeId) {
        Author author =getAuthor(authorId);
        ZipCode zipCode = zipCodeService.getZipCode(zipCodeId);
        if(Objects.nonNull(author.getZipCode())){
            throw new IllegalArgumentException("author has already has a zipcode");
        }
        author.setZipCode(zipCode);
        return mapper.authorToAuthorResponseDto(author);
    }
    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeToauthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipCode(null);
        return mapper.authorToAuthorResponseDto(author);

    }
}
