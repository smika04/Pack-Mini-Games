package com.example.packminigames.Mapping.DB.GameIcon;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameIconMapper;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;

import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat; // Рекомендується AssertJ для кращої читабельності тестів

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameIconEntityMapperTest extends AbstractEntityMapperTest<IGameIconMapper, GameIconDTO, GameIconEntity>
{
    @Override
    protected Class<IGameIconMapper> getMapperClass() {
        return IGameIconMapper.class;
    }

    // Допоміжні методи для створення тестових даних
    private GameIconDTO createGameIconDTO()
    {
        return GameIconDTO.builder()
                .id(1L)
                .name("test_icon")
                .iconData(new byte[]{0x01, 0x02, 0x03})
                .contentType("image/png")
                .gameId(10L)
                .build();
    }

    private GameIconEntity createGameIconEntity()
    {
        TypeGameEntity typeGameEntity = TypeGameEntity.builder().id(101L).name("Adventure").build();
        GameIconEntity gameIconEntity = GameIconEntity.builder().id(301L).iconData("icon_bytes_2".getBytes()).build();

        GameEntity gameEntity_temp = GameEntity.builder()
                .id(10L)
                .title("The Witcher 3")
                .description("An open-world action RPG.")
                .typeGame(typeGameEntity)
                .records(Collections.emptyList())
                .gameIcon(gameIconEntity)
                .build();

        return GameIconEntity.builder()
                .id(1L)
                .name("test_icon")
                .iconData(new byte[]{0x01, 0x02, 0x03})
                .contentType("image/png")
                .game(gameEntity_temp)
                .build();
    }

    @Test
    void toEntity_shouldMapAllFieldsCorrectly()
    {
        GameIconDTO dto = createGameIconDTO();
        GameIconEntity entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getName()).isEqualTo(dto.getName());
        assertThat(entity.getContentType()).isEqualTo(dto.getContentType());
        assertThat(entity.getIconData()).isEqualTo(dto.getIconData());
    }

    @Test
    void toDto_shouldMapAllFieldsCorrectly()
    {
        GameIconEntity entity = createGameIconEntity();
        GameIconDTO dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getContentType()).isEqualTo(entity.getContentType());
        assertThat(dto.getIconData()).isEqualTo(entity.getIconData());
    }

    @Test
    void toEntityList_shouldMapAllElements()
    {
        List<GameIconDTO> dtoList = Arrays.asList(createGameIconDTO(), createGameIconDTO());
        dtoList.get(1).setId(2L);

        List<GameIconEntity> entityList = mapper.toEntityList(dtoList);

        assertThat(entityList).isNotNull().hasSize(2);
        assertThat(entityList.get(0).getId()).isEqualTo(dtoList.get(0).getId());
        assertThat(entityList.get(1).getId()).isEqualTo(dtoList.get(1).getId());
    }

    @Test
    void toDtoList_shouldMapAllElements()
    {
        List<GameIconEntity> entityList = Arrays.asList(createGameIconEntity(), createGameIconEntity());
        entityList.get(1).setId(2L);

        List<GameIconDTO> dtoList = mapper.toDtoList(entityList);

        assertThat(dtoList).isNotNull().hasSize(2);
        assertThat(dtoList.get(0).getId()).isEqualTo(entityList.get(0).getId());
        assertThat(dtoList.get(1).getId()).isEqualTo(entityList.get(1).getId());
    }
}
