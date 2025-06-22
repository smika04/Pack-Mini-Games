package com.example.packminigames.Mapping.DB.Record;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordEntityMapperTest extends AbstractEntityMapperTest<IRecordMapper, RecordDTO, RecordEntity>
{
    @Override
    protected Class<IRecordMapper> getMapperClass() {return IRecordMapper.class;}

    private RecordDTO createRecordDTO() {
        return RecordDTO.builder()
                .id(1L)
                .gameId(101)
                .userId(201)
                .score(1500)
                .datePlayed(new Date()).build();
    }

    private RecordEntity createRecordEntity()
    {
        return RecordEntity.builder()
                .id(1L)
                .score(1500)
                .time(Duration.ofMinutes(5))
                .game(GameEntity.builder().id(101L).build())
                .user(UserEntity.builder().id(201L).build()).build();
    }

    @Test
    void toEntity_shouldMapDirectFieldsAndSetAssociationsToNull()
    {
        RecordDTO dto = createRecordDTO();
        RecordEntity entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getScore()).isEqualTo(dto.getScore());

        assertThat(entity.getGame()).isNull();
        assertThat(entity.getUser()).isNull();
        assertThat(entity.getTime()).isNull();
    }

    @Test
    void toDto_shouldMapDirectFieldsAndAssociationIds()
    {
        RecordEntity entity = createRecordEntity();
        RecordDTO dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getScore()).isEqualTo(entity.getScore());

        assertThat(dto.getGameId()).isEqualTo(entity.getGame().getId().intValue());
        assertThat(dto.getUserId()).isEqualTo(entity.getUser().getId().intValue());

        assertThat(dto.getDatePlayed()).isNull();
    }

    @Test
    void toEntityList_shouldMapAllElements()
    {
        List<RecordDTO> dtoList = Arrays.asList(createRecordDTO(), createRecordDTO());
        dtoList.get(1).setId(2L);
        dtoList.get(1).setGameId(102);

        List<RecordEntity> entityList = mapper.toEntityList(dtoList);

        assertThat(entityList).isNotNull().hasSize(2);
        assertThat(entityList.get(0).getId()).isEqualTo(dtoList.get(0).getId());
        assertThat(entityList.get(1).getId()).isEqualTo(dtoList.get(1).getId());
        assertThat(entityList.get(0).getGame()).isNull();
        assertThat(entityList.get(1).getGame()).isNull();
    }

    @Test
    void toDtoList_shouldMapAllElements()
    {
        List<RecordEntity> entityList = Arrays.asList(createRecordEntity(), createRecordEntity());
        entityList.get(1).setId(2L);
        entityList.get(1).setGame(GameEntity.builder().id(102L).build());

        List<RecordDTO> dtoList = mapper.toDtoList(entityList);

        assertThat(dtoList).isNotNull().hasSize(2);
        assertThat(dtoList.get(0).getId()).isEqualTo(entityList.get(0).getId());
        assertThat(dtoList.get(1).getId()).isEqualTo(entityList.get(1).getId());
        assertThat(dtoList.get(0).getGameId()).isEqualTo(entityList.get(0).getGame().getId().intValue());
        assertThat(dtoList.get(1).getGameId()).isEqualTo(entityList.get(1).getGame().getId().intValue());
    }
}
