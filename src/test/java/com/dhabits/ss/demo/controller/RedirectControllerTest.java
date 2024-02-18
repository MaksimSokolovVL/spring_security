package com.dhabits.ss.demo.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RedirectController.class)
class RedirectControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    @WithMockUser(authorities = "ROLE_ADMIN")
    void endpointWhenAdminAuthorityThenAuthorized() {
        this.mvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void endpointWhenUserAuthorityThenAuthorized() {
        this.mvc.perform(get("/user"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void anyWhenUnauthenticatedThenUnauthorized() {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

}