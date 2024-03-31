package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectRegistrationRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.domain.model.emun.UserRole;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final ResourceObjectService objectService;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public String addNewResourceObject(ResourceObjectRegistrationRq user, Model model) {
        ResourceObjectDto byUsername = objectService.findByValue(user.getValue());
        List<RoleDto> allRoles = roleService.findAllRoles();

        if (!isNull(byUsername)) {
            model.addAttribute("user_message", "User exists!");
            return "registration";
        }



        var resourceObjectSaveRq = ResourceObjectSaveRq.builder()
                .value(user.getValue())
                .name(user.getName())
                .path(user.getPath())
                .age(user.getAge())
                .password(encoder.encode(user.getPassword()))
                .active(true)
                .build();

        if (allRoles.isEmpty()) {
            resourceObjectSaveRq.setRoles(List.of(UserRole.USER.getName()));
        } else {
            RoleEntity byRoleName = roleService.findByRoleName(user.getName()).orElse(null);
            resourceObjectSaveRq.setRoles(List.of(byRoleName != null ? byRoleName.getRoleName() : UserRole.USER.getName()));
        }
        Long savedResource = objectService.saveResourceObject(resourceObjectSaveRq);
        log.info("Created User by id: " + savedResource);

        return "redirect:/login";
    }


}
