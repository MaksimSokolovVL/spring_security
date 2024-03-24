package com.dhabits.ss.demo.domain.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceObjectRq extends ResourceObjectBase {
    private String roles;

    public ResourceObjectRq(ResourceObjectDto dto) {
        super(dto);
        this.roles = dto.getRoles().stream()
                .map(RoleDto::getRoleName)
                .map(roleName -> roleName.replace("ROLE_", ""))
                .collect(Collectors.joining(", "));
    }

}
