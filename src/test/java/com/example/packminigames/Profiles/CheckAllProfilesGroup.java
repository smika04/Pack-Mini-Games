package com.example.packminigames.Profiles;

import com.example.packminigames.Profiles.CheckDatabaseConfiguration.AllDatabaseIntegrationTestsSuite;
import com.example.packminigames.Profiles.CheckProfiles.ProfileExistenceVerificationIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Database Configuration Integration Tests")
@SelectClasses({
        AllDatabaseIntegrationTestsSuite.class,
        ProfileExistenceVerificationIT.class
})
public class CheckAllProfilesGroup {
}
