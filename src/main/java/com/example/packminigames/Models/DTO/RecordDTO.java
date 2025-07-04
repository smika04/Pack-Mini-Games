package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordDTO implements IIdentifiableDTO
{
    private Long id;
    private Long gameId;
    private Long userId;
    private int score;
    private LocalDateTime datePlayed;
}
