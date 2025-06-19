package com.example.packminigames.Controller.REST_Controllers;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IREST_ControllerDAO<D>
{
    ResponseEntity<D> create(D dto);
    ResponseEntity<D> getById(Long id);
    ResponseEntity<List<D>> getAll();
    ResponseEntity<D> update(Long id,D dto);
    ResponseEntity <Void> delete(Long id);
}
