package com.developia.bookstore.controller;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/findByIsbn")
    public String findByIsbn(Model model, @RequestParam("isbn") String isbn) {
        Book book = bookService.findByIsbn(isbn);
        model.addAttribute("book", book);
        return "bookUpdate";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Book book) {
        bookService.create(book);
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("isbn") String isbn) {
        bookService.delete(isbn);
        return "redirect:/home";
    }
}
