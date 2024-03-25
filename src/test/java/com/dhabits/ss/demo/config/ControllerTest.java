package com.dhabits.ss.demo.config;

import com.dhabits.ss.demo.config.security.WebSecurityConfig;
import com.dhabits.ss.demo.controller.AdminController;
import com.dhabits.ss.demo.controller.ResourceController;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest({
        AdminController.class,
        ResourceController.class
})
@Import({WebSecurityConfig.class, TestSecurityConfig.class})
public abstract class ControllerTest {
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;
    @MockBean
    protected RoleService roleService;
    @MockBean
    protected UserService userService;
    @MockBean
    protected ResourceObjectService resourceObjectService;
}
