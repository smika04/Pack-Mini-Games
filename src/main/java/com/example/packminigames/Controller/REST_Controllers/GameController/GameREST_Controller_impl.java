package com.example.packminigames.Controller.REST_Controllers.GameController;

import com.example.packminigames.Models.DTO.GameDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameREST_Controller_impl implements IGameREST_Controller
{
    @Override
    public ResponseEntity<GameDTO> create(GameDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<GameDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<GameDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<GameDTO> update(Long id, GameDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
