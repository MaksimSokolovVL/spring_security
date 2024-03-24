package com.dhabits.ss.demo.controller;

import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import com.dhabits.ss.demo.domain.model.ResourceObjectEditObjectRq;
import com.dhabits.ss.demo.service.impl.ResourceObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ResourceRestController {
    private final ResourceObjectService resourceObjectService;

    @GetMapping("/user/{id}")
    public ResourceObjectDto getUser(@PathVariable long id) {
        return resourceObjectService.getResourceObjectDtoById(id);
    }

    @PutMapping("/user")
    public ResourceObjectDto editUser(@RequestBody ResourceObjectEditObjectRq upUser) {
        return resourceObjectService.updateResourceObject(upUser);
    }

    @DeleteMapping("/user/{id}")
    public String deleteEmployee(@PathVariable long id) {
        return resourceObjectService.deleteObjectById(id);
    }
}
