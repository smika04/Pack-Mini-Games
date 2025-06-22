package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Models.Entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IBasicMapper<UserDTO, UserEntity> {
}
