package com.example.webservices.restfulwebservices.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "content", "created_at"})
public record PostResponseDto(
        UUID id, @JsonProperty("created_at") LocalDateTime createdAt, String title, String content
) {}
