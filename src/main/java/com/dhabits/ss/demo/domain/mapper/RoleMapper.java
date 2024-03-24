package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper extends Mappable<RoleEntity, RoleDto> {

}
