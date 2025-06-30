package com.example.packminigames.Controller.REST_Controllers;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IREST_ControllerDAO<DTO>
{
    ResponseEntity<DTO> create(DTO dto);
    ResponseEntity<DTO> getById(Long id);
    ResponseEntity<List<DTO>> getAll();
    ResponseEntity<DTO> update(Long id,DTO dto);
    ResponseEntity <Void> delete(Long id);
}
