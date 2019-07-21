package com.demo.SpringBoot.web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSourceDB")
    public DataSource dataSourceDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/springboot?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("Admin123");
        dataSource.setMaxIdle(20);
        dataSource.setInitialSize(3);
        dataSource.setValidationQuery("select now()");
        dataSource.setMaxTotal(30);
        return dataSource;
    }
}
