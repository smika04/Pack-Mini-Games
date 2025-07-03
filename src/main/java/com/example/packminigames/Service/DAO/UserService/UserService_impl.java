package com.example.packminigames.Service.DAO.UserService;

import com.example.packminigames.Mapping.DB.IUserMapper;
import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService_impl implements IUserService
{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public List<UserDTO> GetAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public Optional<UserDTO> GetById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public UserDTO Add(UserDTO dto)
    {
        UserEntity userEntity = userMapper.toEntity(dto);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toDto(savedEntity);

    }

    @Override
    public UserDTO Update(UserDTO dto)
    {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update operation.");
        }

        UserEntity existingUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + dto.getId() + " not found for update."));

        userMapper.updateEntityFromDto(dto, existingUser);

        UserEntity updatedEntity = userRepository.save(existingUser);

        return userMapper.toDto(updatedEntity);
    }

    @Override
    public void Delete(Long id)
    {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Сутність з ID " + id + " не знайдена.");
        }

        userRepository.deleteById(id);
    }
}
