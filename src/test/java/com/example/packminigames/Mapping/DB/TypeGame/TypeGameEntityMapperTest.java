package com.example.packminigames.Mapping.DB.TypeGame;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.ITypeGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.springframework.boot.test.context.SpringBootTest;

public class TypeGameEntityMapperTest extends AbstractEntityMapperTest<ITypeGameMapper, TypeGameDTO, TypeGameEntity>
{
    @Override
    protected Class<ITypeGameMapper> getMapperClass() {return ITypeGameMapper.class;}
}
