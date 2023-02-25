package com.example.webservices.restfulwebservices.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, LocalDate birthDate) {}
