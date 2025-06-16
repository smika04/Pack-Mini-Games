package com.example.packminigames.Profiles.CheckDatabaseConfiguration;

import com.example.packminigames.Models.Domain.DatabaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = AbstractDatabaseConfigIT.MinimalTestConfig.class)
public class AbstractDatabaseConfigIT
{
    @TestConfiguration
    @EnableConfigurationProperties(DatabaseProperties.class)
    static class MinimalTestConfig {
        // Тут не потрібні додаткові біни чи сканування компонентів,
        // оскільки DatabaseProperties вже є @Component і буде знайдено через @EnableConfigurationProperties
    }


    @Autowired
    protected DatabaseProperties databaseProperties;

    protected void assertDatabaseProperties(String expectedUrl,String expectedHost, String expectedName, String expectedUsername, String expectedPassword, String expectedDriverClassName)
    {
        assertEquals(expectedUrl, databaseProperties.getUrl(),
                "URL бази даних, сформований з .env, не відповідає URL, завантаженому з конфігурації профілю Spring.");

        assertEquals(expectedUsername, databaseProperties.getUsername(),
                "Ім'я користувача з .env не відповідає імені користувача, завантаженому з конфігурації профілю Spring.");

        assertEquals(expectedPassword, databaseProperties.getPassword(),
                "Пароль з .env не відповідає паролю, завантаженому з конфігурації профілю Spring.");

        assertEquals(expectedDriverClassName, databaseProperties.getDriverClassName(),
                "Ім'я драйвера з .env не відповідає імені драйвера, завантаженому з конфігурації профілю Spring.");

        assertTrue(databaseProperties.getUrl().contains(expectedHost),
                "URL бази даних не містить очікуваного хоста з .env.");
        assertTrue(databaseProperties.getUrl().contains(expectedName),
                "URL бази даних не містить очікуваного імені бази даних з .env.");
    }
}
