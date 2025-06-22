package com.example.packminigames.Mapping.DB.GameIcon;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameIconMapper;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameIconEntity;
import org.springframework.boot.test.context.SpringBootTest;

public class GameIconEntityMapperTest extends AbstractEntityMapperTest<IGameIconMapper, GameIconDTO, GameIconEntity>
{
    @Override
    protected Class<IGameIconMapper> getMapperClass() {return IGameIconMapper.class;}
}
