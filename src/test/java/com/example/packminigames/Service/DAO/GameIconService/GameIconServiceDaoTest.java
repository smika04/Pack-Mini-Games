package com.example.packminigames.Service.DAO.GameIconService;

import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Service.DAO.AbstractServiceDaoTest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("Database Configuration for Prod Profile")
public class GameIconServiceDaoTest extends AbstractServiceDaoTest
{
}
