package com.example.packminigames.Repository.UserRepository;

import com.example.packminigames.Models.Entity.TypeGameEntity;
import com.example.packminigames.Models.Entity.UserEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IBaseRepository<UserEntity, Long>
{
    Optional<UserEntity> findByUsername(String username);
}
