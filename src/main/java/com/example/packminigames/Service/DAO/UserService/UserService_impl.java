package com.example.packminigames.Service.DAO.UserService;

import com.example.packminigames.Models.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService_impl implements IUserService
{
    @Override
    public List<UserDTO> getAll() {
        return null; // Заглушка
    }

    @Override
    public Optional<UserDTO> getById(Long id)
    {
        return Optional.empty(); // Заглушка
    }

    @Override
    public UserDTO add(UserDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public UserDTO update(UserDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public void delete(Long id)
    {
    }
}
