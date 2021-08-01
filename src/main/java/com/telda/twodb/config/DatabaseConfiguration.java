package com.telda.twodb.config;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    public static final String First_datasource = "OneDS";
    public static final String Second_datasource = "TwoDS";

    @Bean(name = First_datasource,destroyMethod = "")
    @ConfigurationProperties(prefix = "datasource.one")
    @Primary
    public DataSource dataSourceOne()
    {
        return new HikariDataSource();
    }
    @Bean(name = Second_datasource,destroyMethod = "")
    @ConfigurationProperties(prefix = "datasource.two")
    @Primary
    public DataSource dataSourceTwo()
    {
        return new HikariDataSource();
    }

}

