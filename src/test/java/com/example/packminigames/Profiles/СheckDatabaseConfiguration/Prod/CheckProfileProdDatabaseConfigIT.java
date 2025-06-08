package com.example.packminigames.Profiles.СheckDatabaseConfiguration.Prod;

import com.example.packminigames.PackMiniGamesApplication;
import com.example.packminigames.Profiles.СheckDatabaseConfiguration.AbstractDatabaseConfigIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(classes = PackMiniGamesApplication.class)
@ActiveProfiles("prod")
@DisplayName("Database Configuration for prod Profile")
public class CheckProfileProdDatabaseConfigIT extends AbstractDatabaseConfigIT
{
    private static final String EXPECTED_DB_HOST = "test_host_prod";
    private static final String EXPECTED_DB_NAME = "test_db_prod";
    private static final String EXPECTED_DB_USERNAME = "test_user_prod";
    private static final String EXPECTED_DB_PASSWORD = "test_password_prod";
    private static final String EXPECTED_DRIVER_CLASS_NAME = "org.postgresql.Driver";


    @DynamicPropertySource
    static void setTestProperties(DynamicPropertyRegistry registry) {
        registry.add("DB_PROD_HOST", () -> EXPECTED_DB_HOST);
        registry.add("DB_ROD_NAME", () -> EXPECTED_DB_NAME);
        registry.add("DB_ROD_USERNAME", () -> EXPECTED_DB_USERNAME);
        registry.add("DB_PROD_PASSWORD", () -> EXPECTED_DB_PASSWORD);

        // Якщо драйвер також може бути встановлений через ENV, додайте:
        // registry.add("DB_DRIVER_CLASS_NAME", () -> EXPECTED_DRIVER_CLASS_NAME);
    }

    @Test
    void shouldLoadDevDatabaseProperties() {
        assertDatabaseProperties(EXPECTED_DB_HOST, EXPECTED_DB_NAME, EXPECTED_DB_USERNAME,
                EXPECTED_DB_PASSWORD, EXPECTED_DRIVER_CLASS_NAME);
    }
}
