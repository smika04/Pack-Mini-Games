package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameIconDTO implements IIdentifiableDTO
{
    private Long id;
    private String name;
    private byte[] iconData;
    private String contentType;
    private Long gameId;
}

