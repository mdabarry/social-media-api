package com.example.webservices.restfulwebservices.service;

import com.example.webservices.restfulwebservices.exception.UserNotFoundException;
import com.example.webservices.restfulwebservices.model.User;
import com.example.webservices.restfulwebservices.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }
}

