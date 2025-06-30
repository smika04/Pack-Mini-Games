package com.example.packminigames.Service;

import com.example.packminigames.Service.DAO.AllServiceDaoIntegrationTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Database Configuration Integration Tests")
@SelectClasses({
        AllServiceDaoIntegrationTest.class
})
public class AllServiceIntegrationTest {
}
