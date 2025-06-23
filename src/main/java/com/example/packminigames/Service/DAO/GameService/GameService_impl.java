package com.example.packminigames.Service.DAO.GameService;

import com.example.packminigames.Mapping.DB.IGameMapper;
import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService_impl implements IGameService
{
    private final IGameRepository gameRepository;
    private final IGameMapper gameMapper;

    @Override
    public List<GameDTO> GetAll()
    {
        List<GameEntity> gameEntities = gameRepository.findAll();
        return gameMapper.toDtoList(gameEntities);
    }

    @Override
    public Optional<GameDTO> GetById(Long id) {
        return gameRepository.findById(id).map(gameMapper::toDto);
    }

    @Override
    public GameDTO Add(GameDTO dto)
    {
        GameEntity gameEntity = gameMapper.toEntity(dto);
        GameEntity savedEntity = gameRepository.save(gameEntity);

        return gameMapper.toDto(savedEntity);
    }

    @Override
    public GameDTO Update(GameDTO dto)
    {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update operation.");
        }

        Optional<GameEntity> existingEntityOptional = gameRepository.findById(dto.getId());

        if (existingEntityOptional.isPresent()) {
            GameEntity existingEntity = existingEntityOptional.get();

            gameMapper.updateEntityFromDto(dto, existingEntity);

            GameEntity updatedEntity = gameRepository.save(existingEntity);

            // 4. Перетворюємо оновлену сутність назад на DTO і повертаємо
            return gameMapper.toDto(updatedEntity);
        } else {
            // Якщо сутність не знайдена, кидаємо виняток
            throw new EntityNotFoundException("Game with ID " + dto.getId() + " not found for update.");
        }
    }

    @Override
    public void Delete(Long id) {gameRepository.deleteById(id);}
}
