package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(RoleEntity entity);

    RoleEntity toEntity(RoleDto dto);

    List<RoleDto> toDtoList(List<RoleEntity> entities);

    List<RoleEntity> toEntityList(List<RoleDto> dtos);
}
