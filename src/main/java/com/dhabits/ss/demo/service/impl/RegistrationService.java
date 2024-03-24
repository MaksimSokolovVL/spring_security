package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.ResourceObjectRegistrationRq;
import com.dhabits.ss.demo.domain.model.emun.UserRole;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import com.dhabits.ss.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ResourceObjectRepository resourceRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public String addNewResourceObject(ResourceObjectRegistrationRq user, Model model) {
        Optional<ResourceObject> byUsername = resourceRepo.findByValue(user.getValue());
        List<RoleEntity> allRoles = roleRepo.findAll();

        if (byUsername.isPresent()) {
            model.addAttribute("user_message", "User exists!");
            return "registration";
        }

        var resourceObject = ResourceObject.builder()
                .value(user.getValue())
                .name(user.getName())
                .path(user.getPath())
                .age(user.getAge())
                .password(encoder.encode(user.getPassword()))
                .active(true)
                .build();

        if (allRoles.isEmpty()) {
            resourceObject.setRoles(List.of(createdRole()));
        } else {
            RoleEntity byRoleName = roleRepo.findByRoleName(user.getName()).orElse(null);
            resourceObject.setRoles(List.of(byRoleName != null ? byRoleName : createdRole()));
        }
        ResourceObject savedResource = resourceRepo.save(resourceObject);
        log.info("Created User by id: " + savedResource.getId());

        return "redirect:/login";
    }

    private RoleEntity createdRole() {
        return new RoleEntity(UserRole.USER.getName());
    }

}
