package com.example.packminigames.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeGameDTO implements IIdentifiableDTO
{
    private Long id;
    private String name;
}
