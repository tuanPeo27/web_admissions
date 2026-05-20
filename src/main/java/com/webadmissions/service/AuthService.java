package com.webadmissions.service;

import com.webadmissions.model.User;
import com.webadmissions.repository.UserRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> authenticate(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return Optional.empty();
        }
        return userRepository.findActiveByUsernameAndPassword(username.trim(), password);
    }
}
