package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDto> findAllRoles();

    RoleEntity save(RoleEntity roleEntity);

    Optional<RoleEntity> findByRoleName(String s);

}
