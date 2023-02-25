package com.example.webservices.restfulwebservices.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiException(Exception ex, WebRequest request) {
        return handleException(ex, request, null, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Object> handlePersonNotFoundException(Exception ex, WebRequest request) {
        return handleException(ex, request, ex.getMessage(), NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        String message = Arrays.toString(ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).toArray());
        return handleException(ex, request, message, BAD_REQUEST);
    }

    private ResponseEntity<Object> handleException(
            Exception ex, WebRequest request, String message, HttpStatus status
    ) {
        log.debug(ex.getMessage());
        ex.printStackTrace();

        ApiExceptionEntity entity = ApiExceptionEntity.builder()
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .message(message)
                .details(request.getDescription(false))
                .build();

        return new ResponseEntity<>(entity, status);
    }

}
