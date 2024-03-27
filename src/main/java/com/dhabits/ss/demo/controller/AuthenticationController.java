package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.model.web.AccessToken;
import com.dhabits.ss.demo.domain.model.web.LoginRq;
import com.dhabits.ss.demo.service.AuthJwt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthJwt authJwt;

    @PostMapping("/access_token")
    public AccessToken getToken(@Valid @RequestBody LoginRq loginRq) {
        log.error("### ->  ->  -> {}", loginRq);
        return authJwt.authenticate(loginRq);
    }

}
