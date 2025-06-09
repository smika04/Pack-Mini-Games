package com.example.packminigames.Service.DAO.GameService;

import com.example.packminigames.Models.DTO.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService_impl implements IGameService
{
    @Override
    public List<GameDTO> getAll() {
        return null; // Заглушка
    }

    @Override
    public Optional<GameDTO> getById(Long id)
    {
        return Optional.empty(); // Заглушка
    }

    @Override
    public GameDTO add(GameDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public GameDTO update(GameDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public void delete(Long id)
    {
    }
}
