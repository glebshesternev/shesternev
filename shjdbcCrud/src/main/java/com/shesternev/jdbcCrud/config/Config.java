package com.shesternev.jdbcCrud.config;

import com.shesternev.jdbcCrud.model.User;
import com.shesternev.jdbcCrud.repository.MyCrudRepository;
import com.shesternev.jdbcCrud.repository.UserRepository;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${dataSource.driver}")
    private String driverClassName;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;
    @Value("${dataSource.initial.size}")
    private int initialSize;
    @Value("${dataSource.max.active}")
    private int maxActive;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean
    public MyCrudRepository<Integer, User> userRepository() {
        return new UserRepository(dataSource());
    }
}
