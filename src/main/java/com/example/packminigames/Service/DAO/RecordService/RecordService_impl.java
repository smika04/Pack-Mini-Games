package com.example.packminigames.Service.DAO.RecordService;

import com.example.packminigames.Models.DTO.GameDTO;
import com.example.packminigames.Models.DTO.RecordDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RecordService_impl implements IRecordService
{
    @Override
    public List<RecordDTO> getAll() {
        return null; // Заглушка
    }

    @Override
    public Optional<RecordDTO> getById(Long id)
    {
        return Optional.empty(); // Заглушка
    }

    @Override
    public RecordDTO add(RecordDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public RecordDTO update(RecordDTO dto)
    {
        return null; // Заглушка
    }

    @Override
    public void delete(Long id)
    {
    }
}
