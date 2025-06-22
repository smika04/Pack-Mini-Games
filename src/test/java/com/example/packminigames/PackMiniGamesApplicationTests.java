package com.example.packminigames;

import com.example.packminigames.Mapping.CheckAllMappers;
import com.example.packminigames.Profiles.CheckAllProfilesGroup;
import com.example.packminigames.Profiles.CheckDatabaseConfiguration.AllDatabaseIntegrationTestsSuite;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Suite
@SpringBootTest
@ActiveProfiles("test")
@SelectClasses({
        AllDatabaseIntegrationTestsSuite.class,
        CheckAllProfilesGroup.class,
        CheckAllMappers.class
})
class PackMiniGamesApplicationTests {

    @Test
    void contextLoads()
    {
    }
}
