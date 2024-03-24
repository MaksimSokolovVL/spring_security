package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    List<ResourceObjectDto> getAllResourceObject();


}
