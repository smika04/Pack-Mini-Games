package com.example.packminigames.Profiles.СheckDatabaseConfiguration;

import com.example.packminigames.Config.JpaConfig;
import com.example.packminigames.Models.Domain.DatabaseProperties;
import com.example.packminigames.PackMiniGamesApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ContextConfiguration(classes = PackMiniGamesApplication.class)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        FlywayAutoConfiguration.class,
//JpaConfig.class
})
@SpringBootTest
public class AbstractDatabaseConfigIT
{
    @Autowired
    protected DatabaseProperties databaseProperties;

    protected void assertDatabaseProperties(String expectedHost, String expectedName, String expectedUsername,
                                            String expectedPassword, String expectedDriverClassName) {
        String expectedUrl = String.format("jdbc:postgresql://%s:5432/%s", expectedHost, expectedName);
        assertThat(databaseProperties.getUrl()).isEqualTo(expectedUrl);
        assertThat(databaseProperties.getUsername()).isEqualTo(expectedUsername);
        assertThat(databaseProperties.getPassword()).isEqualTo(expectedPassword);
        assertThat(databaseProperties.getDriverClassName()).isEqualTo(expectedDriverClassName);
    }

}
