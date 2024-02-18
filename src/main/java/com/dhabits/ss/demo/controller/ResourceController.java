package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.RoleDto;
import com.dhabits.ss.demo.service.RoleService;
import com.dhabits.ss.demo.service.impl.ResourceObjectService;
import com.dhabits.ss.demo.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final ResourceObjectService service;
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Long> createResourceObject(@RequestBody ResourceObjectDto object) {
        val resultId = service.save(object);
        return ok(resultId);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResourceObjectDto> getResourceObject(@PathVariable Long id) {
        return ok(service.get(id));
    }

    @PostMapping("/role")
    public ResponseEntity<List<RoleDto>> getRoles(){
        return ok(roleService.findAllRoles());
    }

}
