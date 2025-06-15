package com.example.packminigames.Profiles.CheckDatabaseConfiguration;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.Dev.CheckProfileDevDatabaseConfigIT;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.Prod.CheckProfileProdDatabaseConfigIT;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.Test.CheckProfileTestDatabaseConfigIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@Suite
@SuiteDisplayName("All Database Configuration Integration Tests")
@SelectClasses({
        CheckProfileDevDatabaseConfigIT.class,
        CheckProfileProdDatabaseConfigIT.class,
        CheckProfileTestDatabaseConfigIT.class
})
public class AllDatabaseIntegrationTestsSuite {
}
