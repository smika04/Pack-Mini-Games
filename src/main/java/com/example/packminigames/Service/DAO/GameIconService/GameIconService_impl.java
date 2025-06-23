package com.example.packminigames.Service.DAO.GameIconService;

import com.example.packminigames.Mapping.DB.IGameIconMapper;
import com.example.packminigames.Models.DTO.GameIconDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Repository.GameIconRepository.IGameIconRepository;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameIconService_impl implements IGameIconService
{
    private final IGameIconRepository gameIconRepository;
    private final IGameRepository gameRepository;

    private final IGameIconMapper gameIconMapper;

    @Override
    public List<GameIconDTO> GetAll() {
        List<GameIconEntity> gameIconEntities = gameIconRepository.findAll();
        return gameIconMapper.toDtoList(gameIconEntities);
    }

    @Override
    public Optional<GameIconDTO> GetById(Long id) {
        return gameIconRepository.findById(id).map(gameIconMapper::toDto);
    }

    @Override
    public GameIconDTO Add(GameIconDTO dto)
    {
        GameIconEntity gameIconEntity = gameIconMapper.toEntity(dto);

        GameEntity game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game with ID " + dto.getGameId() + " not found for icon association."));

        gameIconEntity.setGame(game);

        GameIconEntity savedEntity = gameIconRepository.save(gameIconEntity);

        return gameIconMapper.toDto(savedEntity);

    }

    @Override
    public GameIconDTO Update(GameIconDTO dto)
    {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update operation.");
        }

        GameIconEntity existingGameIcon = gameIconRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("GameIcon with ID " + dto.getId() + " not found for update."));

        gameIconMapper.updateEntityFromDto(dto, existingGameIcon);

        // Перевіряємо, чи змінився gameId, і оновлюємо GameEntity, якщо потрібно
        if (!existingGameIcon.getGame().getId().equals(dto.getGameId())) {
            GameEntity newGame = gameRepository.findById(dto.getGameId())
                    .orElseThrow(() -> new EntityNotFoundException("Game with ID " + dto.getGameId() + " not found for updating icon association."));
            existingGameIcon.setGame(newGame);
        }

        GameIconEntity updatedEntity = gameIconRepository.save(existingGameIcon);

        return gameIconMapper.toDto(updatedEntity);
    }

    @Override
    public void Delete(Long id) {gameIconRepository.deleteById(id);}
}