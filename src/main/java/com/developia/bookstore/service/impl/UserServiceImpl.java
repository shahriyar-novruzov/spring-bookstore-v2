package com.developia.bookstore.service.impl;

import com.developia.bookstore.exception.AccessDeniedException;
import com.developia.bookstore.exception.NotFoundException;
import com.developia.bookstore.model.Session;
import com.developia.bookstore.model.User;
import com.developia.bookstore.model.enums.Role;
import com.developia.bookstore.repository.UserRepository;
import com.developia.bookstore.service.SessionService;
import com.developia.bookstore.service.UserService;
import com.developia.bookstore.util.PasswordHasher;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final PasswordHasher passwordHasher;

    public UserServiceImpl(UserRepository userRepository,
                           SessionService sessionService,
                           PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void register(User user) {

        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new IllegalArgumentException("Password mismatch");

        String hashPassword = passwordHasher.hash(user.getPassword().toCharArray());
        user.setRole(Role.USER);
        user.setPassword(hashPassword);
        user.setConfirmPassword(hashPassword);

        userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        Session session = sessionService.findActiveSession();
        if (session != null) return session.getUser();

        User user;

        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new NotFoundException(username + " not found");
        }

        String hashPassword = passwordHasher.hash(password.toCharArray());

        if (!user.getPassword().equals(hashPassword))
            throw new AccessDeniedException("Incorrect password");

        sessionService.create(user);

        return user;
    }

    @Override
    public void logout() {
        sessionService.delete();
    }
}
