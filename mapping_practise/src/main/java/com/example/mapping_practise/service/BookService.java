package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.requestDTO.BookRequestDto;
import com.example.mapping_practise.dto.responseDTO.BookResponseDto;
import com.example.mapping_practise.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BookResponseDto addBook(BookRequestDto bookRequestDto);

    BookResponseDto getBookById(Long bookId);

    List<BookResponseDto> getBooks();
    Book getBook(Long bookId);

    BookResponseDto deleteBook(Long bookId);

    BookResponseDto editBook(Long bookId,BookRequestDto bookRequestDto);

    BookResponseDto addUthorToBook(Long bookId,Long authorId);

    BookResponseDto deleteAuthorFromBook(Long bookId,Long authorId);

    BookResponseDto addCategoryToBook(Long bookId,Long categoryId);

    BookResponseDto removecategoryFromBook(Long bookId,Long categoryId);

}
