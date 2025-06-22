package com.example.packminigames.Mapping.DB.User;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.IUserMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import org.springframework.boot.test.context.SpringBootTest;

public class UserEntityMapperTest extends AbstractEntityMapperTest<IUserMapper, UserDTO, UserEntity>
{
    @Override
    protected Class<IUserMapper> getMapperClass() {return IUserMapper.class;}
}
