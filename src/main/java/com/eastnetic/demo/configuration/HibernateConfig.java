package com.eastnetic.demo.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Autowired
    ResourceLoader resourceLoader;

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        java.util.Properties applicationProperties = new Properties();
        applicationProperties.load(resourceLoader.getResource("classpath:application.properties").getInputStream());
        Properties hbmProperties = new Properties();
        hbmProperties.setProperty("hibernate.connection.url", applicationProperties.getProperty("spring.datasource.url"));
        hbmProperties.setProperty("hibernate.connection.username", applicationProperties.getProperty("spring.datasource.username"));
        hbmProperties.setProperty("hibernate.connection.password", applicationProperties.getProperty("spring.datasource.password"));
        // will create sessionFactory bean to generate session
        return new org.hibernate.cfg.Configuration().configure().addProperties(hbmProperties).buildSessionFactory();
    }
}
