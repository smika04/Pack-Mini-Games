package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.RecordEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRecordMapper extends IBasicMapper<RecordDTO, RecordEntity>
{
}
