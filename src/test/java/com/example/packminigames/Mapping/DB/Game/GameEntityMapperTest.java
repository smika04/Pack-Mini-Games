package com.example.packminigames.Mapping.DB.Game;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;

public class GameEntityMapperTest extends AbstractEntityMapperTest<IGameMapper, GameDTO, GameEntity>
{
    @Override
    protected Class<IGameMapper> getMapperClass() {return IGameMapper.class;}
}
