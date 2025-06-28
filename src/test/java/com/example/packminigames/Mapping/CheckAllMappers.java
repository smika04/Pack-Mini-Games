package com.example.packminigames.Mapping;

import com.example.packminigames.Mapping.DB.CheckAllEntityMappersTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("All Mappers Tests")
@SelectClasses({
        CheckAllEntityMappersTest.class
})
public class CheckAllMappers {
}
