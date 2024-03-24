package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.mapper.RoleMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectRegistrationRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.domain.model.emun.UserRole;
import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.service.UserService;
import com.dhabits.ss.demo.service.impl.ResourceObjectService;
import com.dhabits.ss.demo.utils.JoinedRolesUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final ResourceObjectService resourceObjectService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ResourceObjectMapper objectMapper;



    @GetMapping
    public String adminPanel(Model model, Authentication authentication) {

        ResourceObject resourceObject = (ResourceObject) authentication.getPrincipal();
        ResourceObjectDto objectDto = objectMapper.toDto(resourceObject);

        model.addAttribute("rolesList", JoinedRolesUtil.joinRoles(resourceObject));
        model.addAttribute("userIn", objectDto);


        List<ResourceObjectDto> users = userService.getAllResourceObject();
        List<ResourceObjectRq> list = users.stream().map(ResourceObjectRq::new).toList();
        model.addAttribute("users", list);

        addNewUser(model);

        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {

        ResourceObjectSaveRq tempUser = (ResourceObjectSaveRq) model.getAttribute("tempUser");

        ResourceObjectSaveRq user = tempUser == null ? new ResourceObjectSaveRq() : tempUser;

        List<RoleDto> allDistinctRoles = roleService.findAllRoles();

        model.addAttribute("edit_user", user);
        model.addAttribute("allRoles", allDistinctRoles);

        return "add_user";
    }

    @PostMapping("/saveUser")
    public String saveResourceObject(
            @Valid @ModelAttribute("edit_user") ResourceObjectSaveRq saveRq,
            BindingResult bindingResult, Model model
    ) {

        ResourceObjectDto resourceObjectDto = resourceObjectService.findByValue(saveRq.getValue());
        if (resourceObjectDto != null) {
            model.addAttribute("user_message", "Login exists!");
            model.addAttribute("tempUser", saveRq);
            return addNewUser(model);
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/add_user";
        }

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

        resourceObjectService.save(resourceObject);

        return "redirect:/admin";
    }

}
