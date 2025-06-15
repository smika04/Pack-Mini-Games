package com.example.packminigames.Repository.RecordRepository;

import com.example.packminigames.Models.Entity.GameEntity;
import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRecordRepository extends IBaseRepository<RecordEntity, Long>
{
}
