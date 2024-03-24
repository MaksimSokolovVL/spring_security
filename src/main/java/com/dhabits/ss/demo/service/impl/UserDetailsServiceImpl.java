package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.Mappable;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import com.dhabits.ss.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserService {

    private final ResourceObjectRepository resourceRepo;
    private final Mappable<ResourceObject, ResourceObjectDto> mapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResourceObject resource = resourceRepo.findByValue(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("There is no resource with this LOGIN: %s", username)));

        Hibernate.initialize(resource.getRoles());

        return resource;
    }


    @Override
    public List<ResourceObjectDto> getAllResourceObject() {
        return resourceRepo.findAll().stream().map(mapper::toDto).toList();
    }


}
