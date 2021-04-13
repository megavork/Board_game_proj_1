package com.example.Board_game_proj_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

@Configuration
public class AuthenticationProviderConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "userDetailService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select username, password from users where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select user_role from users where username=?");
        return jdbcDao;
    }
}
