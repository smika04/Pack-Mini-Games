package com.example.packminigames.Profiles.СheckDatabaseConfiguration;

import com.example.packminigames.Profiles.СheckDatabaseConfiguration.Dev.CheckProfileDevDatabaseConfigIT;
import com.example.packminigames.Profiles.СheckDatabaseConfiguration.Prod.CheckProfileProdDatabaseConfigIT;
import com.example.packminigames.Profiles.СheckDatabaseConfiguration.Test.CheckProfileTestDatabaseConfigIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Database Configuration Integration Tests")
@SelectClasses({
        CheckProfileDevDatabaseConfigIT.class,
        CheckProfileProdDatabaseConfigIT.class,
        CheckProfileTestDatabaseConfigIT.class
})
public class AllDatabaseIntegrationTestsSuite {
}
