package com.developia.bookstore.controller;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.User;
import com.developia.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    private final BookService bookService;

    public PageController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/bookPage")
    public String bookPage() {
        return "bookPage";
    }

    @GetMapping("/bookCreate")
    public String bookCreate(Model model) {
        model.addAttribute("book", new Book());
        return "bookCreate";
    }

    @GetMapping("/bookUpdate")
    public String bookUpdate(Model model) {
        model.addAttribute("book", new Book());
        return "bookUpdate";
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }
}
