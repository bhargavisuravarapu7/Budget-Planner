package com.budget_planner.budget_backend.service;

import org.springframework.stereotype.Service;

import com.budget_planner.budget_backend.model.User;
import com.budget_planner.budget_backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        return userRepository.save(user);
    }

    public User signin(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
