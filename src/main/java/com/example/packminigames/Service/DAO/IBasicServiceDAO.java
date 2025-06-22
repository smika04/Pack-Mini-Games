package com.example.packminigames.Service.DAO;


import java.util.List;
import java.util.Optional;

public interface IBasicServiceDAO<D>
{
    List<D> GetAll();
    Optional<D> GetById(Long id);
    D Add(D dto);
    D Update(D dto);
    void Delete(Long id);
}
