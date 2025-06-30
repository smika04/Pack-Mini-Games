package com.example.packminigames.Controllers.REST.DAO;

import com.example.packminigames.Controllers.REST.DAO.GameController.GameControllerRESTDaoTest;
import com.example.packminigames.Controllers.REST.DAO.GameIconController.GameIconControllerRESTDaoTest;
import com.example.packminigames.Controllers.REST.DAO.RecordController.RecordControllerRESTDaoTest;
import com.example.packminigames.Controllers.REST.DAO.TypeGameController.TypeGameControllerRESTDaoTest;
import com.example.packminigames.Controllers.REST.DAO.UserController.UserControllerRESTDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Controllers REST DAO Integration Tests")
@SelectClasses({
        GameControllerRESTDaoTest.class,
        GameIconControllerRESTDaoTest.class,
        RecordControllerRESTDaoTest.class,
        TypeGameControllerRESTDaoTest.class,
        UserControllerRESTDaoTest.class
})
public class AllControllerRESTDaoTest { }
