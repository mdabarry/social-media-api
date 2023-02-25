package com.example.webservices.restfulwebservices.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequestDto(
        @Size(min = 2, max = 50, message = "Field name should have length between 2 and 50") String name,
        @Past(message = "Field birthDate should be in the past") @JsonProperty("birth_date") LocalDate birthDate
) {}
