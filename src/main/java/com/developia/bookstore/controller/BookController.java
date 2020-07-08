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

    @PostMapping(value = "/update", params = "action=Update") // /book/update
    public String update(@ModelAttribute Book book) {
        System.out.println("update");
        bookService.update(book);
        return "redirect:/home";
    }

    @PostMapping(value = "/update", params = "action=Delete")
    public String delete(@ModelAttribute Book book) {
        bookService.delete(book.getIsbn());
        return "redirect:/home";
    }

    @GetMapping(value = "/view")
    public String view(Model model, @RequestParam("isbn") String isbn) {
        Book book = bookService.findByIsbn(isbn);
        model.addAttribute("book", book);
        return "bookView";
    }

    @PostMapping("/addToCart")
    public String create(@RequestParam("isbn") String isbn) {
        bookService.addToCart(isbn);
        return "redirect:/home";
    }
}
