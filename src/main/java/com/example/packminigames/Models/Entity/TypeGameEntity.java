package com.example.packminigames.Models.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeGameEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, unique = true)
    public String name;

    public String description;

    @OneToMany(mappedBy = "typeGame", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<GameEntity> games = new ArrayList<>();
}