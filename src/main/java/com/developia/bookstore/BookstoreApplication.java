package com.developia.bookstore;

import com.developia.bookstore.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);

        User user = new User();

        user.setFirstName("");
        user.setEmail("");
    }
}
