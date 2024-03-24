package com.dhabits.ss.demo.domain.mapper;

import java.util.List;

public interface Mappable<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    List<D> toDtoList(List<E> entities);

    List<E> toEntityList(List<D> dtos);
}
