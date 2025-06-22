package com.example.packminigames.Mapping.DB.Game;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameEntityMapperTest extends AbstractEntityMapperTest<IGameMapper, GameDTO, GameEntity>
{
    @Override
    protected Class<IGameMapper> getMapperClass() {return IGameMapper.class;}

    @Test
    @DisplayName("Should map GameDTO to GameEntity correctly with all flattened fields")
    void testToEntityMapping_fullObject()
    {
        GameDTO dto = GameDTO.builder()
                .id(1L)
                .title("Cyberpunk 2077")
                .description("A dystopian open-world RPG.")
                .typeGameName("Action RPG")
                .iconData("icon_bytes_1".getBytes()).build();

        GameEntity entity = mapper.toEntity(dto);

        assertNotNull(entity, "Mapped entity should not be null");

        assertEquals(dto.getId(), entity.getId(), "ID should be mapped correctly");
        assertEquals(dto.getTitle(), entity.getTitle(), "Name should be mapped correctly");
        assertEquals(dto.getDescription(), entity.getDescription(), "Description should be mapped correctly");

        assertNotNull(entity.getTypeGame(), "TypeGameEntity should not be null when typeGameName is present");
        assertEquals(dto.getTypeGameName(), entity.getTypeGame().getName(), "typeGameName should be mapped to TypeGameEntity.name");

        assertNotNull(entity.getGameIcon(), "GameIconEntity should not be null when iconData is present");
        assertArrayEquals(dto.getIconData(), entity.getGameIcon().getIconData(), "iconData should be mapped to GameIconEntity.data");

        assertNotNull(entity.getRecords(), "Records list should not be null (default to empty)");
        assertTrue(entity.getRecords().isEmpty(), "Records list should be empty if not mapped from DTO");
    }

    @Test
    @DisplayName("Should map GameEntity to GameDTO correctly with all flattened fields")
    void testToDtoMapping_fullObject()
    {
        TypeGameEntity typeGameEntity = TypeGameEntity.builder().id(101L).name("Adventure").build();
        GameIconEntity gameIconEntity = GameIconEntity.builder().id(301L).iconData("icon_bytes_2".getBytes()).build();

        GameEntity entity = GameEntity.builder()
                .id(2L)
                .title("The Witcher 3")
                .description("An open-world action RPG.")
                .typeGame(typeGameEntity)
                .records(Collections.emptyList())
                .gameIcon(gameIconEntity)
                .build();

        gameIconEntity.setGame(entity);

        GameDTO dto = mapper.toDto(entity);

        assertNotNull(dto, "Mapped DTO should not be null");

        assertEquals(entity.getId(), dto.getId(), "ID should be mapped correctly");
        assertEquals(entity.getTitle(), dto.getTitle(), "Name should be mapped to Name");
        assertEquals(entity.getDescription(), dto.getDescription(), "Description should be mapped correctly");

        assertEquals(entity.getTypeGame().getName(), dto.getTypeGameName(), "TypeGameEntity.name should be mapped to typeGameName");

        assertArrayEquals(entity.getGameIcon().getIconData(), dto.getIconData(), "GameIconEntity.data should be mapped to iconData");

        assertNull(dto.getRecords(), "Records list should be null in DTO as it is not mapped");
    }

    @Test
    @DisplayName("Should map GameEntity to GameDTO with null typeGame entity")
    void testToDtoMapping_nullTypeGameEntity()
    {
        GameEntity entity = GameEntity.builder()
                .id(3L)
                .title("Game without Type Entity")
                .description("A simple game.")
                .typeGame(null)
                .records(Collections.emptyList())
                .gameIcon(null)
                .build();

        GameDTO dto = mapper.toDto(entity);
        assertNotNull(dto);
        assertNull(dto.getTypeGameName(), "typeGameName should be null if TypeGameEntity is null");
    }

    @Test
    @DisplayName("Should map GameEntity to GameDTO with null gameIcon entity")
    void testToDtoMapping_nullGameIconEntity()
    {
        GameEntity entity = GameEntity.builder()
                .id(4L)
                .title("Game without Icon Entity")
                .description("Another simple game.")
                .typeGame(TypeGameEntity.builder().id(102L).name("Casual").build())
                .records(Collections.emptyList())
                .gameIcon(null)
                .build();

        GameDTO dto = mapper.toDto(entity);
        assertNotNull(dto);
        assertNull(dto.getIconData(), "iconData should be null if GameIconEntity is null");
    }

    @Test
    @DisplayName("Should map GameDTO to GameEntity with null typeGameName")
    void testToEntityMapping_nullTypeGameName()
    {
        GameDTO dto = GameDTO.builder()
                .id(5L)
                .title("Game with null Type Name")
                .description("A game.")
                .typeGameName(null)
                .iconData(null)
                .build();

        GameEntity entity = mapper.toEntity(dto);
        assertNotNull(entity);
        assertNull(entity.getTypeGame(), "TypeGameEntity should be null if typeGameName is null");
    }

    @Test
    @DisplayName("Should map GameDTO to GameEntity with null iconData")
    void testToEntityMapping_nullIconData()
    {
        GameDTO dto = GameDTO.builder()
                .id(6L)
                .title("Game with null Icon Data")
                .description("Another game.")
                .typeGameName("Strategy")
                .iconData(null)
                .build();

        GameEntity entity = mapper.toEntity(dto);
        assertNotNull(entity);
        assertNull(entity.getGameIcon(), "GameIconEntity should be null if iconData is null");
    }

}
