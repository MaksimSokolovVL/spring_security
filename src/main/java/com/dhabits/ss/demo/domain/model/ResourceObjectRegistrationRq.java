package com.dhabits.ss.demo.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceObjectRegistrationRq {
    private String value;
    private String name;
    private String path;
    private Integer age;
    @NotNull
    private String password;
}
