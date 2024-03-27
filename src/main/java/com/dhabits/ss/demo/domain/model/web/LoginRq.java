package com.dhabits.ss.demo.domain.model.web;

import jakarta.validation.constraints.NotBlank;

public record LoginRq(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
