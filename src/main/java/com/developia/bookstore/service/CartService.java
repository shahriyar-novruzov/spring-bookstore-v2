package com.developia.bookstore.service;

import com.developia.bookstore.model.Cart;

public interface CartService {
    Cart findActiveCart();

    void addBook(String isbn);

    void removeBook(String isbn);
}
