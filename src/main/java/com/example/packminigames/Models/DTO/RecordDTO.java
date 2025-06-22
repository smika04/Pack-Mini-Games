package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordDTO
{
    private Long id;
    private int gameId;
    private int userId;
    private int score;
    private Date datePlayed;
}
