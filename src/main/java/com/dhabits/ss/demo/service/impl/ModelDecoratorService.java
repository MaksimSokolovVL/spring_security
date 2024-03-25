package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.service.UserService;
import com.dhabits.ss.demo.utils.JoinedRolesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModelDecoratorService {
    private final UserService userService;
    private final RoleService roleService;
    private final ResourceObjectMapper objectMapper;
    private final AuthService authService;
    private final ResourceObjectService resourceObjectService;

    public void editAdminModel(Model model, Authentication authentication) {
        ResourceObject resourceObject = authService.getCurrentUser(authentication);
        model.addAttribute("rolesList", JoinedRolesUtil.joinRoles(resourceObject));
        model.addAttribute("userIn", objectMapper.toDto(resourceObject));
        List<ResourceObjectRq> listObjectRq = userService.getPreparedResourceObjectRq();
        model.addAttribute("users", listObjectRq);
        prepareNewUserModel(model);
    }

    public void prepareUserModel(Model model, Authentication authentication) {
        ResourceObject resourceObject = (ResourceObject) authentication.getPrincipal();
        ResourceObjectDto objectDto = objectMapper.toDto(resourceObject);

        model.addAttribute("rolesList", JoinedRolesUtil.joinRoles(resourceObject));
        model.addAttribute("userIn", objectDto);
    }


    public void prepareNewUserModel(Model model) {
        ResourceObjectSaveRq tempUser = (ResourceObjectSaveRq) model.getAttribute("tempUser");
        ResourceObjectSaveRq user = tempUser == null ? new ResourceObjectSaveRq() : tempUser;
        List<RoleDto> allDistinctRoles = roleService.findAllRoles();
        model.addAttribute("edit_user", user);
        model.addAttribute("allRoles", allDistinctRoles);
    }

    public String saveResourceObject(ResourceObjectSaveRq saveRq, BindingResult bindingResult, Model model) {
        ResourceObjectDto resourceObjectDto = resourceObjectService.findByValue(saveRq.getValue());
        if (resourceObjectDto != null) {
            model.addAttribute("user_message", "Login exists!");
            model.addAttribute("tempUser", saveRq);
            prepareNewUserModel(model);
            return "add_user";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/add_user";
        }
        resourceObjectService.saveResourceObject(saveRq);

        return "redirect:/admin";
    }
}
