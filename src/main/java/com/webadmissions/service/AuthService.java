package com.webadmissions.service;

import com.webadmissions.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidLogin(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }
        return userRepository.findActiveByUsernameAndPassword(username.trim(), password).isPresent();
    }
}
