package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import org.springframework.security.core.Authentication;

public interface AuthResourceObject {
    ResourceObject getCurrentUser(Authentication authentication);
}
