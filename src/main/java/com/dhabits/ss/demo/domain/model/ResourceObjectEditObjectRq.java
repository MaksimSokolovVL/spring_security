package com.dhabits.ss.demo.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResourceObjectEditObjectRq extends ResourceObjectBase {
    private List<RoleDto> roles;
    private String password;
}
