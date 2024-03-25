package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    public ResourceObject getCurrentUser(Authentication authentication) {
        return (ResourceObject) authentication.getPrincipal();
    }
}
