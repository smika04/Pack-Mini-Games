package com.example.packminigames.Service.DAO.TypeGameService;

import com.example.packminigames.Models.DTO.TypeGameDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeGame_impl implements ITypeGameService
{
    @Override
    public List<TypeGameDTO> getAll() {
        return null; // Заглушка
    }

    @Override
    public Optional<TypeGameDTO> getById(Long id)
    {
        return Optional.empty(); // Заглушка
    }

    @Override
    public TypeGameDTO add(TypeGameDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public TypeGameDTO update(TypeGameDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public void delete(Long id)
    {
    }
}
