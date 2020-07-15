package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Session;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.CartService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final SessionService sessionService;
    private final CartRepository cartRepository;
    private final BookService bookService;

    public CartServiceImpl(SessionService sessionService,
                           CartRepository cartRepository, BookService bookService) {
        this.sessionService = sessionService;
        this.cartRepository = cartRepository;
        this.bookService = bookService;
    }

    @Override
    public Cart findActiveCart() {
        Session session = sessionService.findActiveSession();
        if (session == null) return Cart.builder().build();
        User user = session.getUser();
        Cart cart = cartRepository.findByUser(user);
        return cart;
    }

    @Override
    public void addBook(String isbn) {
        Session session = sessionService.findActiveSession();
        if (session == null) return;

        User user = session.getUser();
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        Book book = bookService.findByIsbn(isbn);
        List<Book> booksInCart = cart.getBooks();
        booksInCart.add(book);

        cartRepository.save(cart);
    }

    @Override
    public void removeBook(String isbn) {

        Cart cart = findActiveCart();
        List<Book> books = cart.getBooks();
        List<Book> newList = new ArrayList<>();

        for (Book book : books)
            if (!book.getIsbn().equals(isbn))
                newList.add(book);

        cart.setBooks(newList);
        cartRepository.save(cart);
    }
}
