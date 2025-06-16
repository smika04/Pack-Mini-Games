package com.example.packminigames.Models.Test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestDatabaseConfigProperties
{
    protected String url;
    protected String host;
    protected String name;
    protected String username;
    protected String password;
    protected String driverClassName;
}