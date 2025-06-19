package com.example.packminigames.Controller.REST_Controllers.UserController;

import com.example.packminigames.Models.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserREST_Controller_impl implements IUserREST_Controller{
    @Override
    public ResponseEntity<UserDTO> create(UserDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> update(Long id, UserDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
