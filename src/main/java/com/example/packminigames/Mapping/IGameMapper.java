package com.example.packminigames.Mapping;

import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta")
public interface IGameMapper {

    IGameMapper INSTANCE = Mappers.getMapper(IGameMapper.class);

    GameDTO toDTO(GameEntity entity);

    GameEntity toEntity(GameDTO dto);
}
