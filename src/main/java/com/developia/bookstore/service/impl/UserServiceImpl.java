package com.developia.bookstore.service.impl;

import com.developia.bookstore.exception.AccessDeniedException;
import com.developia.bookstore.exception.NotFoundException;
import com.developia.bookstore.model.User;
import com.developia.bookstore.model.enums.Role;
import com.developia.bookstore.repository.UserRepository;
import com.developia.bookstore.service.SessionService;
import com.developia.bookstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;

    public UserServiceImpl(UserRepository userRepository,
                           SessionService sessionService) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
    }

    @Override
    public void register(User user) {

        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new IllegalArgumentException("Password mismatch");

        user.setRole(Role.USER);
        user.setPassword(encode(user.getPassword()));
        user.setConfirmPassword(encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        User user;

        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new NotFoundException(username + " not found");
        }

        if (!user.getPassword().equals(password))
            throw new AccessDeniedException("Incorrect password");

        sessionService.create(user);

        return user;
    }

    @Override
    public void logout() {
        sessionService.delete();
    }

    private String encode(String password) {
        return "encoded password";
    }
}
