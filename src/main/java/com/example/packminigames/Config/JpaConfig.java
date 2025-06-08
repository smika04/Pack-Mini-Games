package com.example.packminigames.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@EnableTransactionManagement
public class JpaConfig
{
    // Spring Boot зазвичай автоматично створює DataSource.
    // Якщо у вас кілька джерел даних або ви хочете налаштувати його вручну:
    // @Bean
    // public DataSource dataSource() {
    //     DriverManagerDataSource dataSource = new DriverManagerDataSource();
    //     dataSource.setDriverClassName("org.postgresql.Driver");
    //     dataSource.setUrl("jdbc:postgresql://localhost:5432/your_database");
    //     dataSource.setUsername("your_user");
    //     dataSource.setPassword("your_password");
    //     return dataSource;
    // }

    // Конфігурація EntityManagerFactory - відповідає за створення EntityManager
    //@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource); // Використовуємо автоматично конфігурований DataSource
        em.setPackagesToScan("com.example.packminigames.Models.Entity"); // Вказуємо, де шукати ваші @Entity класи

        // Використовуємо стандартний HibernateJpaVendorAdapter
        // Spring Boot зазвичай робить це за вас
        // JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        // em.setJpaVendorAdapter(vendorAdapter);

        // Властивості Hibernate (не обов'язково, якщо використовуєте application.properties)
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // auto, create, create-drop, validate, none
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // Вкажіть діалект вашої БД
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        em.setJpaProperties(properties);

        return em;
    }

    // Конфігурація TransactionManager - керує транзакціями бази даних
    //@Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

}
