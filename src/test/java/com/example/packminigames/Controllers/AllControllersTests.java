package com.example.packminigames.Controllers;

import com.example.packminigames.Controllers.MVC.AllControllerMVCTest;
import com.example.packminigames.Controllers.REST.AllControllerRESTTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Controllers Integration Tests")
@SelectClasses({
        AllControllerMVCTest.class,
        AllControllerRESTTest.class
})
public class AllControllersTests {
}
