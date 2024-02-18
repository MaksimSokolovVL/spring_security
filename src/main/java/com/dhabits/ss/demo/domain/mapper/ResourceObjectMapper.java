package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceObjectMapper {
    ResourceObjectDto toDto(ResourceObject entity);
    ResourceObject toEntity(ResourceObjectDto dto);
    List<ResourceObjectDto> toDtoList(List<ResourceObject> entities);
    List<ResourceObject> toEntityList(List<ResourceObjectDto> dtos);
}
