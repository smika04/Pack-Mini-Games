package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO implements IIdentifiableDTO
{
    private Long id;
    private String title;
    private String description;
    private String typeGameName;
    private byte[] iconData;
    private List<RecordDTO> records;
}
