package com.example.packminigames.Repository;

import com.example.packminigames.Models.Entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IBaseRepository<E, ID> extends JpaRepository<E, ID>
{
    List<E> findByActiveTrue();
    Optional<E> findByName(String name);
}
