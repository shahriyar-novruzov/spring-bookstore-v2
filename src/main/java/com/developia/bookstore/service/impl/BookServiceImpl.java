package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.repository.BookRepository;
import com.developia.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
