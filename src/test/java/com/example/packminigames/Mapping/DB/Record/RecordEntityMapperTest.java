package com.example.packminigames.Mapping.DB.Record;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import org.springframework.boot.test.context.SpringBootTest;

public class RecordEntityMapperTest extends AbstractEntityMapperTest<IRecordMapper, RecordDTO, RecordEntity>
{
    @Override
    protected Class<IRecordMapper> getMapperClass() {return IRecordMapper.class;}
}
