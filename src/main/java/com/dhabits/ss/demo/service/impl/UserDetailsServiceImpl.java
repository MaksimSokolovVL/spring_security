package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import com.dhabits.ss.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserService {

    private final ResourceObjectRepository resourceRepo;

    @Override
    @Transactional(readOnly = true)
    public ResourceObject getUserById(long id) {
        return resourceRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ResourceObject findByUsername(String username) {
        return resourceRepo.findByValue(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResourceObject resource = resourceRepo.findByValue(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("There is no resource with this LOGIN: %s", username)));

        Hibernate.initialize(resource.getRoles());

        return resource;
    }
}
