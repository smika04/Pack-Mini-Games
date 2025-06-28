package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.IBasicMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRecordMapper extends IBasicMapper<RecordDTO, RecordEntity>
{
    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "user.id", target = "userId")
    @Override
    RecordDTO toDto(RecordEntity entity, @Context Object... context);

    @Mapping(target = "game", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Override
    RecordEntity toEntity(RecordDTO dto, @Context Object... context);
}
