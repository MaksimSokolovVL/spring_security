package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.BaseDataTest;
import com.dhabits.ss.demo.domain.entity.RoleEntity;
import com.dhabits.ss.demo.domain.model.RoleDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleMapperTest extends BaseDataTest {
    private final static RoleMapper ROLE_MAPPER = Mappers.getMapper(RoleMapper.class);
    @Test
    @SneakyThrows
    public void dto_to_entity_mapping_test() {
        var roleDto = readJson("input-role-dto.json", RoleDto.class);
        var role = ROLE_MAPPER.toEntity(roleDto);
        var input = OBJECT_MAPPER.writeValueAsString(role);
        var output = readTree("output-role.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void entity_to_dto_mapping_test() {
        var role = readJson("input-role.json", RoleEntity.class);
        var individualDto = ROLE_MAPPER.toDto(role);
        var input = OBJECT_MAPPER.writeValueAsString(individualDto);
        var output = readTree("output-role-dto.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void dto_list_to_entity_list_mapping_test() {
        List<RoleDto> roleDto = readJsonList("input-role-dto_list.json", RoleDto.class);
        var role = ROLE_MAPPER.toEntityList(roleDto);
        var input = OBJECT_MAPPER.writeValueAsString(role);
        var output = readTree("output-role_list.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void entity_list_to_dto_list_mapping_test() {
        List<RoleEntity> role = readJsonList("input-role_list.json", RoleEntity.class);
        var individualDto = ROLE_MAPPER.toDtoList(role);
        var input = OBJECT_MAPPER.writeValueAsString(individualDto);
        var output = readTree("output-role-dto_list.json").toString();
        assertEquals(input, output);
    }
}