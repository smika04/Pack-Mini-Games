package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;

import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring"
        //, uses = IRecordMapper.class
)
public interface IGameMapper extends IBasicMapper<GameDTO, GameEntity>
{
    @Override
    @Mapping(source = "typeGameName", target = "typeGame", qualifiedByName = "mapStringToTypeGameEntity")
    @Mapping(source = "iconData", target = "gameIcon", qualifiedByName = "mapBytesToGameIconEntity")
    GameEntity toEntity(GameDTO dto);

    @Override
    @Mapping(source = "typeGame", target = "typeGameName", qualifiedByName = "mapTypeGameEntityToString")
    @Mapping(source = "gameIcon", target = "iconData", qualifiedByName = "mapGameIconEntityToBytes")
    GameDTO toDto(GameEntity entity);

    // Default методи для конвертації, позначені @Named
    @Named("mapStringToTypeGameEntity")
    default TypeGameEntity mapStringToTypeGameEntity(String typeGameName) {
        if (typeGameName == null) {
            return null;
        }
        TypeGameEntity typeGameEntity = new TypeGameEntity();
        typeGameEntity.setName(typeGameName);
        return typeGameEntity;
    }

    @Named("mapBytesToGameIconEntity")
    default GameIconEntity mapBytesToGameIconEntity(byte[] iconData) {
        if (iconData == null) {
            return null;
        }
        GameIconEntity gameIconEntity = new GameIconEntity();
        gameIconEntity.setIconData(iconData);
        return gameIconEntity;
    }

    // Default методи для зворотного мапування (з Entity в DTO)
    @Named("mapTypeGameEntityToString")
    default String mapTypeGameEntityToString(TypeGameEntity typeGameEntity) {
        return typeGameEntity != null ? typeGameEntity.getName() : null;
    }

    @Named("mapGameIconEntityToBytes")
    default byte[] mapGameIconEntityToBytes(GameIconEntity gameIconEntity) {
        return gameIconEntity != null ? gameIconEntity.getIconData() : null;
    }
}
