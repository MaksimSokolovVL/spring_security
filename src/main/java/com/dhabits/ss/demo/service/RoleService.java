package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.RoleDto;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDto> findAllRoles();

    RoleEntity save(RoleEntity roleEntity);

    Optional<RoleEntity> findByRoleName(String s);

}
