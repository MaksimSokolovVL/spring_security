package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.mapper.RoleMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.repository.RoleRepository;
import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.utils.JoinedRolesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;
    private final RoleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findAllRoles() {
        return mapper.toDtoList(roleRepo.findAll());
    }

    @Override
    @Transactional
    public RoleEntity save(RoleEntity roleEntity) {
        return roleRepo.save(roleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoleEntity> findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

}
