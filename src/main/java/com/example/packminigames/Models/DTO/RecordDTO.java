package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO
{
    public int Id;
    public int GameId;
    public int UserId;
    public int Score;
    public Date DatePlayed;
}
