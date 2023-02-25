package com.example.webservices.restfulwebservices.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PostRequestDto(
        @NotNull(message = "Field created_at is required") @JsonProperty("created_at") LocalDateTime createdAt,
        @Size(min = 2, max = 50, message = "Field title should have length between 2 and 50") String title,
        @NotEmpty(message = "Field content should not be empty") String content
) {}
