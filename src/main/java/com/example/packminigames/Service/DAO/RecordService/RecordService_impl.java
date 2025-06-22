package com.example.packminigames.Service.DAO.RecordService;

import com.example.packminigames.Mapping.DB.IRecordMapper;
import com.example.packminigames.Models.DTO.RecordDTO;
import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.Repository.GameRepository.IGameRepository;
import com.example.packminigames.Repository.RecordRepository.IRecordRepository;
import com.example.packminigames.Repository.UserRepository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RecordService_impl implements IRecordService
{
    private final IRecordRepository recordRepository;
    private final IGameRepository gameRepository;
    private final IUserRepository userRepository;

    private final IRecordMapper recordMapper;

    @Override
    public List<RecordDTO> GetAll() {
        List<RecordEntity> recordEntities = recordRepository.findAll();
        return recordMapper.toDtoList(recordEntities);

    }

    @Override
    public Optional<RecordDTO> GetById(Long id) {
        return recordRepository.findById(id).map(recordMapper::toDto);

    }

    @Override
    public RecordDTO Add(RecordDTO dto)
    {
        RecordEntity recordEntity = recordMapper.toEntity(dto);

        GameEntity game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game with ID " + dto.getGameId() + " not found."));
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + dto.getUserId() + " not found."));

        recordEntity.setGame(game);
        recordEntity.setUser(user);

        RecordEntity savedEntity = recordRepository.save(recordEntity);

        return recordMapper.toDto(savedEntity);
    }

    @Override
    public RecordDTO Update(RecordDTO dto)
    {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update operation.");
        }

        RecordEntity existingRecord = recordRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Record with ID " + dto.getId() + " not found for update."));

        recordMapper.updateEntityFromDto(dto, existingRecord);

        if (!existingRecord.getGame().getId().equals(dto.getGameId())) {
            GameEntity newGame = gameRepository.findById(dto.getGameId())
                    .orElseThrow(() -> new EntityNotFoundException("Game with ID " + dto.getGameId() + " not found for record update."));
            existingRecord.setGame(newGame);
        }

        if (!existingRecord.getUser().getId().equals(dto.getUserId())) {
            UserEntity newUser = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User with ID " + dto.getUserId() + " not found for record update."));
            existingRecord.setUser(newUser);
        }

        RecordEntity updatedEntity = recordRepository.save(existingRecord);

        return recordMapper.toDto(updatedEntity);
    }

    @Override
    public void Delete(Long id) {recordRepository.deleteById(id);}
}
