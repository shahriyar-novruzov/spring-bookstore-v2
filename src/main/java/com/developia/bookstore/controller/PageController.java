package com.developia.bookstore.controller;

import com.developia.bookstore.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
}
