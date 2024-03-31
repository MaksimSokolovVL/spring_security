package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectRq;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<ResourceObjectDto> getAllResourceObject();

    List<ResourceObjectRq> getPreparedResourceObjectRq();
}
