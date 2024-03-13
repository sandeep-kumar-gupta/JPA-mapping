package com.example.mapping_practise.service;

import com.example.mapping_practise.dto.mapper;
import com.example.mapping_practise.dto.requestDTO.BookRequestDto;
import com.example.mapping_practise.dto.responseDTO.BookResponseDto;
import com.example.mapping_practise.model.Author;
import com.example.mapping_practise.model.Book;
import com.example.mapping_practise.model.Category;
import com.example.mapping_practise.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    @Transactional
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if (bookRequestDto.getAuthorId().isEmpty()) {
            throw new IllegalArgumentException("you need atleast one author");

        } else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId : bookRequestDto.getAuthorId()) {
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId() == null) {
            throw new IllegalArgumentException("book atleast one category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);
        Book book1 = bookRepository.save(book);
        return mapper.bookToBookResponseDto(book1);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {

        Book book = getBook(bookId);
        return mapper.bookToBookResponseDto(book);

    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return mapper.booktoBookResponseDtos(books);
    }

    @Override
    public Book getBook(Long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("cannot find book with id " + bookId));

        return book;

    }

    @Override
    @Transactional
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
        return mapper.bookToBookResponseDto(book);

    }

    @Override
    @Transactional
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorId().isEmpty()){
            List<Author> authors = new ArrayList<>();
            for (Long authorId:bookRequestDto.getAuthorId()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId()!=null){
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return  mapper.bookToBookResponseDto(bookToEdit);

    }

    @Override
    @Transactional
    public BookResponseDto addUthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if(author.getBooks().contains(author)){
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return mapper.bookToBookResponseDto(book);

    }

    @Override
    @Transactional
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = authorService.getAuthor(authorId);
        if(!(author.getBooks().contains(book))){
            throw  new IllegalArgumentException("book does not have this author");
        }
        author.removeBook(book);
        book.deleteAuthor(author);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category=categoryService.getCategory(categoryId);
        if(Objects.nonNull(book.getCategory())){
            throw  new IllegalArgumentException("book already has a category");
        }
        book.setCategory(category);
        category.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    @Transactional
    public BookResponseDto removecategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category=categoryService.getCategory(categoryId);
        if(!(Objects.nonNull(book.getCategory()))){
            throw  new IllegalArgumentException("book does not have a categoty to delete");
        }
        book.setCategory(null);
        category.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }
}
