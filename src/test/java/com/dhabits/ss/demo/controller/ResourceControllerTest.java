package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.config.ControllerTest;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ResourceControllerTest extends ControllerTest {
    private List<RoleDto> roles;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();

        when(resourceObjectService.save(any())).thenReturn(5L);
        roles = List.of(new RoleDto("ROLE_ADMIN"), new RoleDto("ROLE_USER"));
        when(roleService.findAllRoles()).thenReturn(roles);

    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin", password = "1", authorities = {"ROLE_ADMIN"})
    void whenAdminWithAuthority_PostResourceRole_ShouldBeAuthorized() {
        mvc.perform(post("/resource/role")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(roles)));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin", password = "1", roles = "ADMIN")
    void whenAdminWithRole_PostResourceRole_ShouldBeAuthorized() {
        mvc.perform(post("/resource/role")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(roles)));
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", password = "1", authorities = {"ROLE_USER"})
    void whenUserWithAuthority_PostResourceRole_ShouldBeForbidden() {
        mvc.perform(post("/resource/role")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden());
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserWithRole_PostResourceRole_ShouldBeForbidden() {
        mvc.perform(post("/resource/role")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}