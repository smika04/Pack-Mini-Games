package com.example.packminigames.Mapping.DB.User;

import com.example.packminigames.Mapping.DB.AbstractEntityMapperTest;
import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Mapping.DB.IUserMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserEntityMapperTest extends AbstractEntityMapperTest<IUserMapper, UserDTO, UserEntity>
{
    @Override
    protected Class<IUserMapper> getMapperClass() {return IUserMapper.class;}

    private UserEntity createUserEntity()
    {
        return UserEntity.builder()
                .id(1L)
                .username("testUser")
                .birthday(LocalDate.of(1990, 1, 1)).build();
    }

    private UserDTO createUserDTO()
    {
        return UserDTO.builder()
                .id(1L)
                .username("testUserDTO")
                .birthday(LocalDate.of(1995, 5, 5)).build();
    }

    @Test
    void toDto_shouldMapAllFields()
    {
        UserEntity entity = createUserEntity();
        UserDTO dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getUsername()).isEqualTo(entity.getUsername());
        assertThat(dto.getBirthday()).isEqualTo(entity.getBirthday());
    }

    @Test
    void toEntity_shouldMapAllFields()
    {
        UserDTO dto = createUserDTO();
        UserEntity entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getUsername()).isEqualTo(dto.getUsername());
        assertThat(entity.getBirthday()).isEqualTo(dto.getBirthday());
    }

}
