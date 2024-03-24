package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.model.ResourceObjectEditObjectRq;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResourceObjectMapperRqToEntity extends Mappable<ResourceObject, ResourceObjectEditObjectRq> {

}