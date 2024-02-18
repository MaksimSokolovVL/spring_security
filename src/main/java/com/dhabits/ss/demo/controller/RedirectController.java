package com.dhabits.ss.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/")
public class RedirectController {
    @GetMapping("admin")
    public ResponseEntity<String> adminCall() {
        return ok("/admin");
    }

    @GetMapping("user")
    public ResponseEntity<String> userCall() {
        return ok("/user");
    }

    @GetMapping
    public ResponseEntity<String> defaultCall() {
        return ok("/  ->  default url");
    }
}
