package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.config.ControllerTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminControllerTest extends ControllerTest {

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
                .andExpect(status().isOk());
    }

}