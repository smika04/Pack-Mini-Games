package com.example.packminigames.Controller.REST_Controllers;

import com.example.packminigames.Models.DTO.IIdentifiableDTO;
import com.example.packminigames.Service.DAO.IBasicServiceDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class AbstractBasicRESTController<SERVICE extends IBasicServiceDAO<DTO>, DTO extends IIdentifiableDTO> implements IREST_ControllerDAO<DTO>
{
    private final SERVICE service;

    @PostMapping
    @Override
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        DTO created = service.Add(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED); // Повертає 201 Created
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DTO> getById(@PathVariable Long id)
    {
        return service.GetById(id)
                .map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)) // Якщо знайдено, повертає 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Якщо не знайдено, повертає 404 Not Found
    }

    @GetMapping
    @Override
    public ResponseEntity<List<DTO>> getAll() {
        List<DTO> list = service.GetAll();
        return new ResponseEntity<>(list, HttpStatus.OK); // Повертає 200 OK зі списком користувачів
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<DTO> update(@PathVariable Long id, @RequestBody DTO dto) {
        dto.setId(id);
        try {
            DTO updated = service.Update(dto);
            return new ResponseEntity<>(updated, HttpStatus.OK); // Повертає 200 OK
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Якщо користувач не знайдений, повертає 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Для невалідних аргументів (наприклад, null ID)
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        try {
            service.Delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Повертає 204 No Content після успішного видалення
        } catch (RuntimeException e) { // Ловимо RuntimeException з Service_impl
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Якщо користувач не знайдений, повертає 404 Not Found
        }
    }
}
