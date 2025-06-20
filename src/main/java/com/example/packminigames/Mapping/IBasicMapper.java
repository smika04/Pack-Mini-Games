package com.example.packminigames.Mapping;

import java.util.List;

public interface IBasicMapper<DTO, Entity>
{
    DTO toDto(Entity entity);
    Entity toEntity(DTO dto);
    List<DTO> toDtoList(Entity entity);
    List<Entity> toEntityList(DTO dto);
}
