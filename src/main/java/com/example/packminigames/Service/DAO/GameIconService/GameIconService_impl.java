package com.example.packminigames.Service.DAO.GameIconService;

import com.example.packminigames.Models.DTO.GameIconDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameIconService_impl implements IGameIcon_Service{
    @Override
    public List<GameIconDTO> getAll() {
        return List.of();
    }

    @Override
    public Optional<GameIconDTO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public GameIconDTO add(GameIconDTO dto) {
        return null;
    }

    @Override
    public GameIconDTO update(GameIconDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
