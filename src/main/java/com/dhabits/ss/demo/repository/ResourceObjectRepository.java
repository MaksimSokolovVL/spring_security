package com.dhabits.ss.demo.repository;

import com.dhabits.ss.demo.domain.entity.ResourceObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ResourceObjectRepository extends JpaRepository<ResourceObject, Long> {
    Optional<ResourceObject> findByValue(String value);
}
