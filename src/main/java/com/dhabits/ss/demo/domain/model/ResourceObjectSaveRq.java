package com.dhabits.ss.demo.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;



@Getter
@SuperBuilder
@NoArgsConstructor
public class ResourceObjectSaveRq extends ResourceObjectBase {
    @NotNull
    private String password;
    @Setter
    private List<String> roles;
}
