package com.example.webservices.restfulwebservices.exception;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(UUID personId) {
        super("No person with id %s".formatted(personId));
    }
}
