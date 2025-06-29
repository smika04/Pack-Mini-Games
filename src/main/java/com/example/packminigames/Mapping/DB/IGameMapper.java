package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;

import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IGameMapper extends IBasicMapper<GameDTO, GameEntity>
{
    @Mapping(source = "typeGame.name", target = "typeGameName")
    @Mapping(source = "gameIcon.iconData", target = "iconData")
    @Override
    GameDTO toDto(GameEntity entity, @Context Object... context);

    @Mapping(target = "typeGame", source = "typeGameName", qualifiedByName = "mapTypeGameEntity")
    @Mapping(target = "gameIcon", source = "iconData", qualifiedByName = "mapGameIconEntity")
    @Mapping(target = "records", expression = "java(dto.getRecords() != null ? recordDTOListToRecordEntityList(dto.getRecords(), context) : new java.util.ArrayList<>())")
    @Override
    GameEntity toEntity(GameDTO dto, @Context Object... context);

    @Named("mapTypeGameEntity")
    default TypeGameEntity mapTypeGameEntity(String typeGameName) {
        if (typeGameName == null) {
            return null;
        }
        TypeGameEntity typeGameEntity = new TypeGameEntity();
        typeGameEntity.setName(typeGameName);
        return typeGameEntity;
    }

    @Named("mapGameIconEntity")
    default GameIconEntity mapGameIconEntity(byte[] iconData) {
        if (iconData == null) {
            return null;
        }
        GameIconEntity gameIconEntity = new GameIconEntity();
        gameIconEntity.setIconData(iconData);
        return gameIconEntity;
    }

    @AfterMapping
    default void afterMappingToEntity(@MappingTarget GameEntity entity) {
        if (entity.getRecords() == null) {
            entity.setRecords(new ArrayList<>());
        }
    }
}
