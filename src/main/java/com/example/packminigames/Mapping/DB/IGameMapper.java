package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IGameMapper extends IBasicMapper<GameDTO, GameEntity>
{

}