package com.example.packminigames.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig
{
    // Якщо ви хочете, щоб Spring Boot автоконфігурував DataSource, видаліть цей метод.
    // В іншому випадку, вам потрібно повністю його налаштувати тут.
    // Приклад повного налаштування вручну (НЕ РЕКОМЕНДУЄТЬСЯ для Spring Boot):
    /*
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver"); // або інший драйвер
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    */

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/packminigames/Models/Entity");

        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Якщо вам потрібні додаткові властивості Hibernate/JPA, додайте їх так:
        Properties properties = new Properties();
        // properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Краще через application.properties
        // properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // Краще через application.properties
        em.setJpaProperties(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

}
