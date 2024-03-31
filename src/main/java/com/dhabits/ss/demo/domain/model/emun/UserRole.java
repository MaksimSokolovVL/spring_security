package com.dhabits.ss.demo.domain.model.emun;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
