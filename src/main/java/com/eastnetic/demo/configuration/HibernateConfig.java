package com.eastnetic.demo.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        java.util.Properties applicationProperties = new Properties();
        applicationProperties.load(new FileInputStream(ResourceUtils.getFile("classpath:application.properties")));
        Properties hbmProperties = new Properties();
        hbmProperties.setProperty("hibernate.connection.url", applicationProperties.getProperty("spring.datasource.url"));
        hbmProperties.setProperty("hibernate.connection.username", applicationProperties.getProperty("spring.datasource.username"));
        hbmProperties.setProperty("hibernate.connection.password", applicationProperties.getProperty("spring.datasource.password"));
        // will create sessionFactory bean to generate session
        return new org.hibernate.cfg.Configuration().configure().addProperties(hbmProperties).buildSessionFactory();
    }
}
