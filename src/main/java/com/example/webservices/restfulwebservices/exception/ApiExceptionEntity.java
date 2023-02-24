package com.example.webservices.restfulwebservices.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class ApiExceptionEntity {
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String details;
}

