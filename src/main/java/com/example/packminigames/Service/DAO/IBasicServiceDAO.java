package com.example.packminigames.Service.DAO;


import java.util.List;
import java.util.Optional;

public interface IBasicServiceDAO<D>
{
    List<D> getAll();
    Optional<D> getById(Long id);
    D add(D dto);
    D update(D dto);
    void delete(Long id);

}
