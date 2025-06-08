package com.example.packminigames;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PackMiniGamesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackMiniGamesApplication.class, args);

        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(PackMiniGamesApplication.class, args);

    }

}
