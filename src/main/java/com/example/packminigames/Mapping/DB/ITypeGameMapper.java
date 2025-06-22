package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITypeGameMapper extends IBasicMapper<TypeGameDTO, TypeGameEntity> {
}
