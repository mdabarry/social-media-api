package com.example.webservices.restfulwebservices.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID userId) {
        super("No user with id %s".formatted(userId));
    }
}
