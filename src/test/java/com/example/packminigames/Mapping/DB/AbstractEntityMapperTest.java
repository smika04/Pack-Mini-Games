package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractEntityMapperTest<MAPPER extends IBasicMapper<DTO, ENTITY>, DTO, ENTITY>
{
    protected MAPPER mapper;

    protected abstract Class<MAPPER> getMapperClass();

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(getMapperClass());
    }

    @Test
    @DisplayName("Should return null DTO when mapping null Entity")
    void testToDto_nullEntity_returnsNull() {
        ENTITY entity = null;
        DTO dto = mapper.toDto(entity);
        assertNull(dto);
    }

    @Test
    @DisplayName("Should return null Entity when mapping null DTO")
    void testToEntity_nullDto_returnsNull() {
        DTO dto = null;
        ENTITY entity = mapper.toEntity(dto);
        assertNull(entity);
    }

    @Test
    @DisplayName("Should return empty DTO list when mapping empty Entity list")
    void testToDtoList_emptyEntityList_returnsEmptyList() {
        List<ENTITY> entities = Collections.emptyList();
        List<DTO> dtos = mapper.toDtoList(entities);
        assertNotNull(dtos);
        assertTrue(dtos.isEmpty());
    }

    @Test
    @DisplayName("Should return empty Entity list when mapping empty DTO list")
    void testToEntityList_emptyDtoList_returnsEmptyList() {
        List<DTO> dtos = Collections.emptyList();
        List<ENTITY> entities = mapper.toEntityList(dtos);
        assertNotNull(entities);
        assertTrue(entities.isEmpty());
    }

    @Test
    @DisplayName("Should return null DTO list when mapping null Entity list")
    void testToDtoList_nullEntityList_returnsNull() {
        List<ENTITY> entities = null;
        List<DTO> dtos = mapper.toDtoList(entities);
        assertNull(dtos);
    }

    @Test
    @DisplayName("Should return null Entity list when mapping null DTO list")
    void testToEntityList_nullDtoList_returnsNull() {
        List<DTO> dtos = null;
        List<ENTITY> entities = mapper.toEntityList(dtos);
        assertNull(entities);
    }
}

