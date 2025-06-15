package com.example.packminigames.Profiles.CheckDatabaseConfiguration.Dev;

import com.example.packminigames.Models.Domain.DevTestDatabaseConfigProperties;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.AbstractDatabaseConfigIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("dev")
@DisplayName("Database Configuration for Dev Profile")
public class CheckProfileDevDatabaseConfigIT extends AbstractDatabaseConfigIT
{
    @Autowired
    private DevTestDatabaseConfigProperties devTestDatabaseConfigProperties;

    @Test
    void shouldLoadDevDatabaseProperties()
    {
        assertDatabaseProperties(
                devTestDatabaseConfigProperties.getUrl(),
                devTestDatabaseConfigProperties.getHost(),
                devTestDatabaseConfigProperties.getName(),
                devTestDatabaseConfigProperties.getUsername(),
                devTestDatabaseConfigProperties.getPassword(),
                devTestDatabaseConfigProperties.getDriverClassName()
        );
    }
}

