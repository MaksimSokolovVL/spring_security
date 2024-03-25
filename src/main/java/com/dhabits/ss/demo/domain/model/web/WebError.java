package com.dhabits.ss.demo.domain.model.web;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class WebError {
    private HttpStatus status;
    private String message;
}
