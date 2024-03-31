package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapperRqToEntity;
import com.dhabits.ss.demo.domain.mapper.RoleMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectEditObjectRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.domain.model.emun.UserRole;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ResourceObjectServiceImpl implements ResourceObjectService {
    private final ResourceObjectRepository resourceObjectRepo;
    private final ResourceObjectMapper objectMapper;
    private final ResourceObjectMapperRqToEntity objectMapperRqTo;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    @Transactional
    public Long save(ResourceObject resourceObject) {
        return resourceObjectRepo.save(resourceObject).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public  ResponseEntity<ResourceObjectDto> getResourceObjectDtoById(Long id) {
        return  ResponseEntity.ok(objectMapper.toDto(resourceObjectRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User with ID %d not found", id)))));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteObjectById(Long id) {
        resourceObjectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with ID %d does not exist in database", id)));

        resourceObjectRepo.deleteById(id);
        return  ResponseEntity.ok(String.format("User with ID = %d was deleted", id));
    }

    @Override
    @Transactional
    public  ResponseEntity<ResourceObjectDto> updateResourceObject(ResourceObjectEditObjectRq upUser) {
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
            RoleEntity existingRole = roleService.findByRoleName(role.getRoleName()).orElse(null);
            roles.add(Objects.requireNonNullElse(existingRole, roleMapper.toEntity(role)));
        }

        ResourceObject editedResourceObject = objectMapperRqTo.toEntity(upUser);
        editedResourceObject.setRoles(roles);
        editedResourceObject.setPassword(passwordEncoder.encode(upUser.getPassword()));
        return  ResponseEntity.ok(objectMapper.toDto(resourceObjectRepo.save(editedResourceObject)));
    }


    @Override
    @Transactional(readOnly = true)
    public ResourceObjectDto findByValue(String value) {
        return objectMapper.toDto(resourceObjectRepo.findByValue(value).orElse(null));
    }



    @Override
    @Transactional
    public Long saveResourceObject(ResourceObjectSaveRq saveRq) {

        if (saveRq.getRoles() == null || saveRq.getRoles().isEmpty()) {
            saveRq.setRoles(List.of(UserRole.USER.getName()));
        }

        var resourceObject = ResourceObject.builder()
                .value(saveRq.getValue())
                .name(saveRq.getName())
                .path(saveRq.getPath())
                .age(saveRq.getAge())
                .roles(saveRq.getRoles().stream().map(RoleEntity::new).toList())
                .password(passwordEncoder.encode(saveRq.getPassword()))
                .active(true)
                .build();

        RoleEntity existingRole = roleService.findByRoleName(saveRq.getRoles().get(0)).orElse(null);
        if (existingRole != null) {
            resourceObject.setRoles(List.of(existingRole));
        } else {
            RoleEntity savedRole = roleService.save(new RoleEntity(saveRq.getRoles().get(0)));
            resourceObject.setRoles(List.of(savedRole));
        }

        return resourceObjectRepo.save(resourceObject).getId();

    }
}


