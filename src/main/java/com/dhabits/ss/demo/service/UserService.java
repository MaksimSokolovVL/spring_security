package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {

    ResourceObject getUserById(long id);

    ResourceObject findByUsername(String username);
}
