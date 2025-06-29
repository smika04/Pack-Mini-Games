package com.example.packminigames.Service.DAO.TypeGameService;

import com.example.packminigames.Mapping.DB.ITypeGameMapper;
import com.example.packminigames.Models.DTO.TypeGameDTO;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.TypeGameRepository.ITypeGameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeGameService_impl implements ITypeGameService
{
    private final ITypeGameRepository typeGameRepository;
    private final ITypeGameMapper typeGameMapper;


    @Override
    public List<TypeGameDTO> GetAll() {
        List<TypeGameEntity> typeGameEntities = typeGameRepository.findAll();
        return typeGameMapper.toDtoList(typeGameEntities);

    }

    @Override
    public Optional<TypeGameDTO> GetById(Long id) {
        return typeGameRepository.findById(id).map(typeGameMapper::toDto);
    }

    @Override
    public TypeGameDTO Add(TypeGameDTO dto)
    {
        TypeGameEntity typeGameEntity = typeGameMapper.toEntity(dto);
        TypeGameEntity savedEntity = typeGameRepository.save(typeGameEntity);
        return typeGameMapper.toDto(savedEntity);

    }

    @Override
    public TypeGameDTO Update(TypeGameDTO dto)
    {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID must not be null for update operation.");
        }

        TypeGameEntity existingTypeGame = typeGameRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("TypeGame with ID " + dto.getId() + " not found for update."));

        typeGameMapper.updateEntityFromDto(dto, existingTypeGame);

        TypeGameEntity updatedEntity = typeGameRepository.save(existingTypeGame);

        return typeGameMapper.toDto(updatedEntity);
    }

    @Override
    public void Delete(Long id)
    {
        if (!typeGameRepository.existsById(id)) {
            throw new RuntimeException("Сутність з ID " + id + " не знайдена.");
        }

        typeGameRepository.deleteById(id);
    }
}
