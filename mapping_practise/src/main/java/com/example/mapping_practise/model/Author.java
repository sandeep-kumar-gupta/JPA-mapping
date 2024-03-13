package com.example.mapping_practise.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "zipcode_id")
    private ZipCode zipCode;

    @ManyToMany(mappedBy = "authors",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    public Author(String name, ZipCode zipCode, List<Book> books) {
        this.name = name;
        this.zipCode = zipCode;
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }
}
