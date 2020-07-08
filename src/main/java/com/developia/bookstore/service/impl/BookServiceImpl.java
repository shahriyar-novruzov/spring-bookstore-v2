package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.Book;
import com.developia.bookstore.model.Cart;
import com.developia.bookstore.model.Session;
import com.developia.bookstore.model.User;
import com.developia.bookstore.repository.BookRepository;
import com.developia.bookstore.repository.CartRepository;
import com.developia.bookstore.service.BookService;
import com.developia.bookstore.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final SessionService sessionService;
    private final CartRepository cartRepository;

    public BookServiceImpl(BookRepository bookRepository, SessionService sessionService,
                           CartRepository cartRepository) {
        this.bookRepository = bookRepository;
        this.sessionService = sessionService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void create(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Book book) {
        Book oldBook = bookRepository.findByIsbn(book.getIsbn());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setDescription(book.getDescription());
        oldBook.setName(book.getName());
        oldBook.setPageSize(book.getPageSize());
        oldBook.setPrice(book.getPrice());
        oldBook.setPublishDate(book.getPublishDate());
        bookRepository.save(oldBook);
    }

    @Override
    public void delete(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        bookRepository.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void addToCart(String isbn) {
        Session session = sessionService.findActiveSession();
        if (session == null) return;

        User user = session.getUser();
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        Book book = findByIsbn(isbn);
        List<Book> booksInCart = cart.getBooks();
        booksInCart.add(book);

        cartRepository.save(cart);
    }
}
