package com.dhabits.ss.demo.repository;

import com.dhabits.ss.demo.domain.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
}
