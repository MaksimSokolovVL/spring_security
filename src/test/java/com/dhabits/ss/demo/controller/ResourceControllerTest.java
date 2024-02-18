package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.service.impl.ResourceObjectService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ResourceController.class)
class ResourceControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ResourceObjectService objectService;

    @MockBean
    private RoleService roleService;

    @BeforeEach
    void setup() {
        when(objectService.save(any())).thenReturn(5L);
    }

    @Test
    @SneakyThrows
    void anyWhenUnauthenticatedThenUnauthorized() {
        this.mvc.perform(post("/resource"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @SneakyThrows
    @WithMockUser(authorities = "ROLE_ADMIN")
    void endpointWhenAdminAuthorityThenAuthorized() {
        this.mvc.perform(post("/resource/role"))
                .andExpect(status().isOk());
    }

//    @Test
//    @SneakyThrows
//    @WithMockUser
//    void endpointWhenUserAuthorityThenAuthorized() {
//        this.mvc.perform(get("/user"))
//                .andExpect(status().isOk());
//    }
}