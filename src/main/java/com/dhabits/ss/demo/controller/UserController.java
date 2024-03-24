package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.utils.JoinedRolesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final ResourceObjectMapper objectMapper;

    @GetMapping
    public String getUserPage(Model model, Authentication authentication) {

        ResourceObject resourceObject = (ResourceObject) authentication.getPrincipal();
        ResourceObjectDto objectDto = objectMapper.toDto(resourceObject);

        model.addAttribute("rolesList", JoinedRolesUtil.joinRoles(resourceObject));
        model.addAttribute("userIn", objectDto);
        return "user";
    }

}
