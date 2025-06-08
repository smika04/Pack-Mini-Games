package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO
{
    private Long id;
    private String name;
    private String description;
    private String typeGameName;
    private byte[] iconData;
}
