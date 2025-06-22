package com.example.packminigames.Mapping;

import org.mapstruct.Context;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface IBasicMapper<DTO, Entity>
{
    DTO toDto(Entity entity, @Context Object... context);
    Entity toEntity(DTO dto, @Context Object... context);

    List<DTO> toDtoList(List<Entity> entities, @Context Object... context);
    List<Entity> toEntityList(List<DTO> dtos, @Context Object... context);

    void updateEntityFromDto(DTO dto, @MappingTarget Entity entity, @Context Object... context);
}
