package com.example.packminigames.Mapping.DB;

import com.example.packminigames.Mapping.DB.Game.GameEntityMapperTest;
import com.example.packminigames.Mapping.DB.GameIcon.GameIconEntityMapperTest;
import com.example.packminigames.Mapping.DB.Record.RecordEntityMapperTest;
import com.example.packminigames.Mapping.DB.TypeGame.TypeGameEntityMapperTest;
import com.example.packminigames.Mapping.DB.User.UserEntityMapperTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Mappers for DTO and Entities Tests")
@SelectClasses({
        GameEntityMapperTest.class,
        GameIconEntityMapperTest.class,
        RecordEntityMapperTest.class,
        TypeGameEntityMapperTest.class,
        UserEntityMapperTest.class
})
public class CheckAllEntityMappersTest {
}
