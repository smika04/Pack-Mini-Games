package com.example.packminigames.Controller.REST_Controllers.UserController;

import com.example.packminigames.Models.DTO.UserDTO;
import com.example.packminigames.Service.DAO.UserService.UserService_impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserREST_Controller_impl implements IUserREST_Controller
{
    private final UserService_impl userService;

    @PostMapping
    @Override
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        UserDTO createdUser = userService.Add(dto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // Повертає 201 Created
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> getById(@PathVariable Long id)
    {
        return userService.GetById(id)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK)) // Якщо знайдено, повертає 200 OK
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Якщо не знайдено, повертає 404 Not Found
    }


    @GetMapping
    @Override
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.GetAll();
        return new ResponseEntity<>(users, HttpStatus.OK); // Повертає 200 OK зі списком користувачів
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
        dto.setId(id);
        try {
            UserDTO updatedUser = userService.Update(dto);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // Повертає 200 OK
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
            userService.Delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Повертає 204 No Content після успішного видалення
        } catch (RuntimeException e) { // Ловимо RuntimeException з UserService_impl
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Якщо користувач не знайдений, повертає 404 Not Found
        }
    }

}
