package com.example.packminigames.Repository.GameRepository;

import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IGameRepository extends IBaseRepository<GameEntity, Long>
{
    Optional<GameEntity> findByName(String name);
}