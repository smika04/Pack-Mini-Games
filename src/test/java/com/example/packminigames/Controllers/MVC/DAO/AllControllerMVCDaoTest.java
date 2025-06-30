package com.example.packminigames.Controllers.MVC.DAO;

import com.example.packminigames.Controllers.MVC.DAO.GameController.GameControllerMVCDaoTest;
import com.example.packminigames.Controllers.MVC.DAO.GameIconController.GameIconControllerMVCDaoTest;
import com.example.packminigames.Controllers.MVC.DAO.RecordController.RecordControllerMVCDaoTest;
import com.example.packminigames.Controllers.MVC.DAO.TypeGameController.TypeGameControllerMVCDaoTest;
import com.example.packminigames.Controllers.MVC.DAO.UserController.UserControllerMVCDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Controllers REST DAO Integration Tests")
@SelectClasses({
        GameControllerMVCDaoTest.class,
        GameIconControllerMVCDaoTest.class,
        RecordControllerMVCDaoTest.class,
        TypeGameControllerMVCDaoTest.class,
        UserControllerMVCDaoTest.class
})
public class AllControllerMVCDaoTest {
}
