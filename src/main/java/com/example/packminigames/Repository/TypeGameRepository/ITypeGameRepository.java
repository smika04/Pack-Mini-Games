package com.example.packminigames.Repository.TypeGameRepository;

import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypeGameRepository extends IBaseRepository<TypeGameEntity, Long>
{
    Optional<TypeGameEntity> findByName(String name);
}
