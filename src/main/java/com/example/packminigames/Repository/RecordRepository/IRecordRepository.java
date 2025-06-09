package com.example.packminigames.Repository.RecordRepository;

import com.example.packminigames.Models.Entity.RecordEntity;
import com.example.packminigames.Repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordRepository extends IBaseRepository<RecordEntity, Long> {
}
