package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.model.ResourceObjectRegistrationRq;
import com.dhabits.ss.demo.service.impl.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addResourceObject(@Validated ResourceObjectRegistrationRq user, Model model) {
        return registrationService.addNewResourceObject(user, model);
    }
}
