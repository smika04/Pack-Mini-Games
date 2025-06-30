package com.example.packminigames.Controllers.REST;

import com.example.packminigames.Controllers.REST.DAO.AllControllerRESTDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Controllers REST Integration Tests")
@SelectClasses({
        AllControllerRESTDaoTest.class
})
public class AllControllerRESTTest {
}
