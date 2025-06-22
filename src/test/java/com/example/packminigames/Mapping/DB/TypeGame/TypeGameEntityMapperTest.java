package com.example.packminigames.Mapping.DB.TypeGame;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.ITypeGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TypeGameEntityMapperTest extends AbstractEntityMapperTest<ITypeGameMapper, TypeGameDTO, TypeGameEntity>
{
    @Override
    protected Class<ITypeGameMapper> getMapperClass() {return ITypeGameMapper.class;}

    private TypeGameEntity createTypeGameEntity()
    {
        return TypeGameEntity.builder().id(1L).name("Action").build();
    }

    private TypeGameDTO createTypeGameDTO()
    {
        return TypeGameDTO.builder().id(1L).name("Strategy").build();
    }

    @Test
    void toDto_shouldMapAllFields()
    {
        TypeGameEntity entity = createTypeGameEntity();
        TypeGameDTO dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getName()).isEqualTo(entity.getName());
    }

    @Test
    void toEntity_shouldMapAllFields()
    {
        TypeGameDTO dto = createTypeGameDTO();
        TypeGameEntity entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getName()).isEqualTo(dto.getName());
    }

}
