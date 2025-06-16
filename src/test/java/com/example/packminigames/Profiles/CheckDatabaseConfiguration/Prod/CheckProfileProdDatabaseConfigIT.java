package com.example.packminigames.Profiles.CheckDatabaseConfiguration.Prod;

import com.example.packminigames.Models.Test.Profiles.Prod.ProdTestDatabaseConfigProperties;
import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.AbstractDatabaseConfigIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("prod")
@DisplayName("Database Configuration for Prod Profile")
public class CheckProfileProdDatabaseConfigIT extends AbstractDatabaseConfigIT
{
    @Autowired
    private ProdTestDatabaseConfigProperties prodTestDatabaseConfigProperties;

    @Test
    void shouldLoadDevDatabaseProperties()
    {
        assertDatabaseProperties(
                prodTestDatabaseConfigProperties.getUrl(),
                prodTestDatabaseConfigProperties.getHost(),
                prodTestDatabaseConfigProperties.getName(),
                prodTestDatabaseConfigProperties.getUsername(),
                prodTestDatabaseConfigProperties.getPassword(),
                prodTestDatabaseConfigProperties.getDriverClassName()
        );
    }
}
