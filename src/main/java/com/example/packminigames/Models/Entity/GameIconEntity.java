package com.example.packminigames.Models.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_icons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameIconEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Можливо, варто розглянути, чи name має бути унікальним, якщо іконка прив'язана до гри
    private String name; // Наприклад, "sword_icon" або "shield_icon"

    @Lob // Зазначає, що це великий об'єкт (BLOB)
    @Column(nullable = false)
    private byte[] iconData; // Масив байтів для зберігання зображення PNG

    @Column(nullable = false)
    private String contentType; // Наприклад, "image/png"

    // Зв'язок One-to-One з GameEntity
    // Ця сторона є "власником" зв'язку, тому тут буде стовпець зовнішнього ключа
    @OneToOne(fetch = FetchType.LAZY) // Завантаження за запитом
    @JoinColumn(name = "game_id", referencedColumnName = "id", unique = true, nullable = false)
    // game_id буде стовпцем зовнішнього ключа в таблиці game_icons
    // unique = true гарантує, що одна іконка може бути пов'язана лише з однією грою
    // nullable = false означає, що кожна іконка повинна бути прив'язана до гри
    private GameEntity game;
}

