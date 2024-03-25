package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.service.impl.ModelDecoratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ModelDecoratorService modelDecoratorService;

    @GetMapping
    public String getUserPage(Model model, Authentication authentication) {
        modelDecoratorService.prepareUserModel(model, authentication);
        return "user";
    }

}
