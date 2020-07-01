package com.developia.bookstore.controller;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        bookService.create(book);
        return "redirect:/home";
    }
}
