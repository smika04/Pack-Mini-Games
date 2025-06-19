package com.example.packminigames.Controller.REST_Controllers.TypeGameController;

import com.example.packminigames.Models.DTO.TypeGameDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/typeGame")
public class TypeGameREST_Controller_impl implements ITypeGameREST_Controller{
    @Override
    public ResponseEntity<TypeGameDTO> create(TypeGameDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<TypeGameDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TypeGameDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<TypeGameDTO> update(Long id, TypeGameDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
