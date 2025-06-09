package com.example.packminigames.Repository.GameIconRepository;

import com.example.packminigames.Models.Entity.GameIcon;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameIconRepository extends IBaseRepository<GameIcon, Long> {
}
