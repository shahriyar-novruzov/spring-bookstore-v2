package com.developia.bookstore.service.impl;

import com.developia.bookstore.model.User;
import com.developia.bookstore.model.enums.Role;
import com.developia.bookstore.repository.UserRepository;
import com.developia.bookstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {

        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new IllegalArgumentException("Password mismatch");

        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
