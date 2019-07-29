package com.example.tvordering.controller;

import com.example.tvordering.exceptions.TvorderingNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
public class CoreExceptionHandler {

    @ExceptionHandler(TvorderingNotFoundException.class)
    public ResponseEntity tvorderingNotFoundException(Exception e) {
        return ResponseEntity.status(404)
                .contentType(APPLICATION_JSON)
                .body(e.getMessage());
    }

}
