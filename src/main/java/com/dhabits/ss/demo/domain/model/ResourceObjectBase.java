package com.dhabits.ss.demo.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class ResourceObjectBase {
    private Long id;
    private String value;
    private String name;
    private String path;
    private int age;
    private boolean active;

    protected ResourceObjectBase(ResourceObjectDto dto) {
        this.id = dto.getId();
        this.value = dto.getValue();
        this.name = dto.getName();
        this.path = dto.getPath();
        this.age = dto.getAge();
        this.active = dto.isActive();
    }
}
