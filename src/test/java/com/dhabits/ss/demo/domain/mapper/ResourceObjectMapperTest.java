package com.dhabits.ss.demo.domain.mapper;

import com.dhabits.ss.demo.domain.BaseDataTest;
import com.dhabits.ss.demo.domain.entity.ResourceObject;
import com.dhabits.ss.demo.domain.model.ResourceObjectDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceObjectMapperTest extends BaseDataTest {
    private final static ResourceObjectMapper ROLE_MAPPER = Mappers.getMapper(ResourceObjectMapper.class);

    @Test
    @SneakyThrows
    public void dto_to_entity_mapping_test() {
        var roleDto = readJson("input-resource-object-dto.json", ResourceObjectDto.class);
        var role = ROLE_MAPPER.toEntity(roleDto);
        var input = OBJECT_MAPPER.writeValueAsString(role);
        var output = readTree("output-resource-object.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void entity_to_dto_mapping_test() {
        var role = readJson("input-resource-object.json", ResourceObject.class);
        var individualDto = ROLE_MAPPER.toDto(role);
        var input = OBJECT_MAPPER.writeValueAsString(individualDto);
        var output = readTree("output-resource-object-dto.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void dto_list_to_entity_list_mapping_test() {
        List<ResourceObjectDto> roleDto = readJsonList("input-resource-object-dto-list.json", ResourceObjectDto.class);
        var role = ROLE_MAPPER.toEntityList(roleDto);
        var input = OBJECT_MAPPER.writeValueAsString(role);
        var output = readTree("output-resource-object-list.json").toString();
        assertEquals(input, output);
    }

    @Test
    @SneakyThrows
    public void entity_list_to_dto_list_mapping_test() {
        List<ResourceObject> role = readJsonList("input-resource-object-list.json", ResourceObject.class);
        var individualDto = ROLE_MAPPER.toDtoList(role);
        var input = OBJECT_MAPPER.writeValueAsString(individualDto);
        var output = readTree("output-resource-object-dto-list.json").toString();
        assertEquals(input, output);
    }
}