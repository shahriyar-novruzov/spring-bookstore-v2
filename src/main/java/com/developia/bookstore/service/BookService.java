package com.developia.bookstore.service;

import com.developia.bookstore.model.Book;

import java.util.List;

public interface BookService {
    void create(Book book);

    List<Book> findAll();
}
