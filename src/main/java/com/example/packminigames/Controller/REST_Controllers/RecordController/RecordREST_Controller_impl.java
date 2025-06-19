package com.example.packminigames.Controller.REST_Controllers.RecordController;

import com.example.packminigames.Models.DTO.RecordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/record")
public class RecordREST_Controller_impl implements IRecordREST_Controller{
    @Override
    public ResponseEntity<RecordDTO> create(RecordDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<RecordDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<RecordDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<RecordDTO> update(Long id, RecordDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
