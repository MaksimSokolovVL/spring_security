package com.dhabits.ss.demo.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceObjectDto {
    private String value;
    private String path;
}
