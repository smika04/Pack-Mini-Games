package com.example.packminigames.Mapping;

import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IGameMapper extends IBasicMapper<GameDTO, GameEntity>
{

}
