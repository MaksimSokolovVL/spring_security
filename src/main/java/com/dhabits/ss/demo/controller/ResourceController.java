package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.mapper.ResourceObjectMapper;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.service.ResourceObjectService;
import com.dhabits.ss.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceObjectService objectService;
    private final RoleService roleService;
    private final ResourceObjectMapper mapper;

    @PostMapping("/role")
    public ResponseEntity<List<RoleDto>> getRoles() {
        return ok(roleService.findAllRoles());
    }

    @PostMapping
    public ResponseEntity<Long> createResourceObject(@RequestBody ResourceObjectDto object) {
        val resultId = objectService.save(mapper.toEntity(object));
        return ok(resultId);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResourceObjectDto> getResourceObject(@PathVariable Long id) {
        return objectService.getResourceObjectDtoById(id);
    }

}
