package com.example.packminigames.Mapping;

import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameIconEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGameIconMapper extends IBasicMapper<GameIconDTO, GameIconEntity>
{

}
