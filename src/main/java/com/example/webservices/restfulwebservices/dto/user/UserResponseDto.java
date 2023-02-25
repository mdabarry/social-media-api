package com.example.webservices.restfulwebservices.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDto(@Order(1) UUID id, String name, @JsonProperty("birth_date") LocalDate birthDate) {}
