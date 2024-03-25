package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import com.dhabits.ss.demo.service.impl.ModelDecoratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ModelDecoratorService modelDecoratorService;


    @GetMapping
    public String adminPanel(Model model, Authentication authentication) {
        modelDecoratorService.editAdminModel(model, authentication);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        modelDecoratorService.prepareNewUserModel(model);
        return "add_user";
    }

    @PostMapping("/saveUser")
    public String saveResourceObject(
            @Valid @ModelAttribute("edit_user") ResourceObjectSaveRq saveRq,
            BindingResult bindingResult,
            Model model
    ) {
        return modelDecoratorService.saveResourceObject(saveRq, bindingResult, model);
    }

}
