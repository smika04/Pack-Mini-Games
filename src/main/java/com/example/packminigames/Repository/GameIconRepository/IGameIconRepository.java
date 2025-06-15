package com.example.packminigames.Repository.GameIconRepository;

import com.example.packminigames.Models.Entity.GameIconEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IGameIconRepository extends IBaseRepository<GameIconEntity, Long>
{
    Optional<GameIconEntity> findByName(String name);
}
