package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.model.web.WebError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<WebError> handlerRuntimeException(RuntimeException exception) {
        return handlerException(HttpStatus.BAD_REQUEST, exception);
    }


    private ResponseEntity<WebError> handlerException(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(
                        WebError.builder()
                                .status(status)
                                .message(exception.getMessage())
                                .build());
    }
}
