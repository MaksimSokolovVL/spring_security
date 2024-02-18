package com.dhabits.ss.demo.service.impl;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.repository.ResourceObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.concurrent.ThreadLocalRandom.current;


@Service
@RequiredArgsConstructor
public class ResourceObjectService {
    private final ResourceObjectRepository repository;
    private final ResourceObjectMapper objectMapper;

    public Long save(ResourceObjectDto resourceObjectDto) {
        return repository.save(ResourceObject.builder()
                .id(current().nextLong())
                .value(resourceObjectDto.getValue())
                .path(resourceObjectDto.getPath())
                .active(true)
                .build()).getId();
    }

    public ResourceObjectDto get(long id) {
        return objectMapper.toDto(repository.findById(id).orElse(null));
    }
}
