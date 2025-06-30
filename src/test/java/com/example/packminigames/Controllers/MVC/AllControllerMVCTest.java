package com.example.packminigames.Controllers.MVC;

import com.example.packminigames.Controllers.MVC.DAO.AllControllerMVCDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Controllers REST Integration Tests")
@SelectClasses({
        AllControllerMVCDaoTest.class
})
public class AllControllerMVCTest {
}
