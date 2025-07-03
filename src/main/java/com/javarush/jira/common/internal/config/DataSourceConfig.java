package com.javarush.jira.common.internal.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "postgresDataSourceProperties")
    @ConfigurationProperties("spring.datasource.postgres")
    @Profile("!test")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = " postgresDataSource")
    @Profile("!test")
    public DataSource postgresDataSource() {
        DataSourceProperties properties = postgresDataSourceProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;

    }

    @Bean(name = "h2DataSourceProperties" )
    @ConfigurationProperties("spring.datasource.h2")
    @Profile("test")
    public DataSourceProperties h2DataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "h2DataSource")
    @Profile("test")
    @Primary
    public DataSource h2DataSource() {
        DataSourceProperties properties = h2DataSourceProperties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;

    }
}
