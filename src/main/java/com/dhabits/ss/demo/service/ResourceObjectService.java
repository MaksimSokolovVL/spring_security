package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectEditObjectRq;
import com.dhabits.ss.demo.domain.model.ResourceObjectSaveRq;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface ResourceObjectService {

    Long save(ResourceObject resourceObject);

    ResponseEntity<ResourceObjectDto> getResourceObjectDtoById(Long id);

    ResponseEntity<String> deleteObjectById(Long id);

    ResponseEntity<ResourceObjectDto> updateResourceObject(ResourceObjectEditObjectRq upUser);

    ResourceObjectDto findByValue(String value);

    Long saveResourceObject(ResourceObjectSaveRq saveRq);
}
