package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRecordMapper extends IBasicMapper<RecordDTO, RecordEntity>
{

}
