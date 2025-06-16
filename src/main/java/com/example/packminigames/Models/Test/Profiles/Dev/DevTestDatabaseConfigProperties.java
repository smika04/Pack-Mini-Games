package com.example.packminigames.Models.Test.Profiles.Dev;
import com.example.packminigames.Models.Test.TestDatabaseConfigProperties;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DevTestDatabaseConfigProperties extends TestDatabaseConfigProperties
{
    @Override
    @Value("${DB_DEV_HOST}")
    public void setHost(String host) {super.setHost(host);}

    @Override
    @Value("${DB_DEV_NAME}")
    public void setName(String name) {super.setName(name);}

    @Override
    @Value("${DB_DEV_USERNAME}")
    public void setUsername(String username) {super.setUsername(username);}

    @Override
    @Value("${DB_DEV_PASSWORD}")
    public void setPassword(String password) {super.setPassword(password);}

    @Override
    @Value("${spring.datasource.driver-class-name}")
    public void setDriverClassName(String driverClassName) {
        super.setDriverClassName(driverClassName);
    }

    @Override
    @Value("jdbc:postgresql://${DB_DEV_HOST}:5432/${DB_DEV_NAME}")
    public void setUrl(String url) {super.setUrl(url);}
}

