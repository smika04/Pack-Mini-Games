package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameIconDTO
{
    private Long id;
    private String name;
    private byte[] iconData;
    private String contentType;
    private Long gameId;
}

