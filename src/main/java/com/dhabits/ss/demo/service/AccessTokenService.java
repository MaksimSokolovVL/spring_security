package com.dhabits.ss.demo.service;

import org.springframework.security.core.Authentication;

public interface AccessTokenService {
    String generateIdToken(Authentication authentication);
}
