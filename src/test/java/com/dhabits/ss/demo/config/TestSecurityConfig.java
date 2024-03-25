package com.dhabits.ss.demo.config;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class TestSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var admin = ResourceObject.builder()
                .id(1L)
                .value("admin")
                .path("path/admin")
                .active(true)
                .password("1")
                .roles(List.of(new RoleEntity("ROLE_ADMIN"), new RoleEntity("ROLE_USER")))
                .build();


        var user = ResourceObject.builder()
                .id(2L)
                .value("user")
                .path("path/user")
                .active(true)
                .password("1")
                .roles(List.of(new RoleEntity("ROLE_USER")))
                .build();

        return new InMemoryUserDetailsManager(Arrays.asList(admin, user));
    }
}
