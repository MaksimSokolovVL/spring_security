package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapperRqToEntity;
import com.dhabits.ss.demo.domain.mapper.RoleMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectEditObjectRq;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import com.dhabits.ss.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
public class ResourceObjectService {
    private final ResourceObjectRepository resourceObjectRepo;
    private final ResourceObjectMapper objectMapper;
    private final ResourceObjectMapperRqToEntity objectMapperRqTo;
    private final RoleRepository roleRepo;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    ;

    @Transactional
    public Long save(ResourceObject resourceObject) {
        return resourceObjectRepo.save(resourceObject).getId();
    }

    @Transactional(readOnly = true)
    public ResourceObjectDto getResourceObjectDtoById(long id) {
        return objectMapper.toDto(resourceObjectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User with ID %d not found", id))));
    }

    @Transactional
    public String deleteObjectById(long id) {
        resourceObjectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with ID %d does not exist in database", id)));

        resourceObjectRepo.deleteById(id);
        return String.format("User with ID = %d was deleted", id);
    }

    @Transactional
    public ResourceObjectDto updateResourceObject(ResourceObjectEditObjectRq upUser) {
        ResourceObject resourceObjectToBase = resourceObjectRepo.findById(upUser.getId())
                .orElseThrow(() -> new RuntimeException(String.format("User with ID %d does not exist in database", upUser.getId())));

        if (isNull(upUser.getPassword()) || upUser.getPassword().isEmpty()) {
            upUser.setPassword(resourceObjectToBase.getPassword());
        }

        if (upUser.getRoles() == null || upUser.getRoles().isEmpty()) {
            throw new RuntimeException("User roles  ->  NULL or EMPTY");
        }

        List<RoleEntity> roles = new ArrayList<>();
        for (RoleDto role : upUser.getRoles()) {
            RoleEntity existingRole = roleRepo.findByRoleName(role.getRoleName()).orElse(null);
            roles.add(Objects.requireNonNullElse(existingRole, roleMapper.toEntity(role)));
        }

        ResourceObject editedResourceObject = objectMapperRqTo.toEntity(upUser);
        editedResourceObject.setRoles(roles);
        editedResourceObject.setPassword(passwordEncoder.encode(upUser.getPassword()));
        return objectMapper.toDto(resourceObjectRepo.save(editedResourceObject));
    }


    @Transactional(readOnly = true)
    public ResourceObjectDto findByValue(String value) {
        return objectMapper.toDto(resourceObjectRepo.findByValue(value).orElse(null));
    }
}


