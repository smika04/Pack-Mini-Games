package com.example.packminigames.Repository.TypeGameRepository;

import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeGameRepository extends IBaseRepository<TypeGameEntity, Long> {
}
