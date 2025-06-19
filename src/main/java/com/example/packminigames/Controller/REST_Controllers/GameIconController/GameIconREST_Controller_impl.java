package com.example.packminigames.Controller.REST_Controllers.GameIconController;

import com.example.packminigames.Models.DTO.GameIconDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game-icon")
public class GameIconREST_Controller_impl implements IGameIconREST_Controller{
    @Override
    public ResponseEntity<GameIconDTO> create(GameIconDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<GameIconDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<GameIconDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<GameIconDTO> update(Long id, GameIconDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
