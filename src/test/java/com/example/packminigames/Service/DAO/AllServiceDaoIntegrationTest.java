package com.example.packminigames.Service.DAO;

import com.example.packminigames.Service.DAO.GameIconService.GameIconServiceDaoTest;
import com.example.packminigames.Service.DAO.GameService.GameServiceDaoTest;
import com.example.packminigames.Service.DAO.RecordService.RecordServiceDaoTest;
import com.example.packminigames.Service.DAO.TypeGameService.TypeGameServiceDaoTest;
import com.example.packminigames.Service.DAO.UserService.UserServiceDaoTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Database Configuration Integration Tests")
@SelectClasses({
        GameIconServiceDaoTest.class,
        GameServiceDaoTest.class,
        RecordServiceDaoTest.class,
        TypeGameServiceDaoTest.class,
        UserServiceDaoTest.class
})
public class AllServiceDaoIntegrationTest {
}
