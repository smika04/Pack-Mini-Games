package com.example.packminigames.Service.DAO;


import java.util.List;
import java.util.Optional;

public interface IBasicServiceDAO<DTO>
{
    List<DTO> GetAll();
    Optional<DTO> GetById(Long id);
    DTO Add(DTO dto);
    DTO Update(DTO dto);
    void Delete(Long id);
}
