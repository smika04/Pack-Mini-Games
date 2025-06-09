package com.example.packminigames.Repository.GameRepository;

import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends IBaseRepository<GameEntity, Long>
{ }