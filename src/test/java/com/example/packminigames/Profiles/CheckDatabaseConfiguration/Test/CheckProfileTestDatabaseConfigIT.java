package com.example.packminigames.Profiles.CheckDatabaseConfiguration.Test;

import com.example.packminigames.Models.Test.Profiles.Test.TestTestDatabaseConfigProperties;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.AbstractDatabaseConfigIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("test")
@DisplayName("Database Configuration for Test Profile")
public class CheckProfileTestDatabaseConfigIT extends AbstractDatabaseConfigIT
{
    @Autowired
    private TestTestDatabaseConfigProperties testTestDatabaseConfigProperties;

    @Test
    void shouldLoadTestDatabaseProperties()
    {
        assertDatabaseProperties(
                testTestDatabaseConfigProperties.getUrl(),
                testTestDatabaseConfigProperties.getHost(),
                testTestDatabaseConfigProperties.getName(),
                testTestDatabaseConfigProperties.getUsername(),
                testTestDatabaseConfigProperties.getPassword(),
                testTestDatabaseConfigProperties.getDriverClassName()
        );
    }
}
