package com.example.Board_game_proj_1.config;

import com.example.Board_game_proj_1.dto.UserDto;
import com.example.Board_game_proj_1.entity.User;
import com.example.Board_game_proj_1.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
public class AuthenticationProviderConfig extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private DataSource dataSource;

    @Autowired
    UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        //
    }

    @Override
    protected User retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        Object token = usernamePasswordAuthenticationToken.getCredentials();
        try {
            User user = (User) Optional
                    .ofNullable(token)
                    .map(String::valueOf)
                    .flatMap(userService::findByToken)
                    .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
            user.unlockAccount();
            return  user;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }



    @Bean(name = "userDetailService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select username, password from users where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select user_role from users where username=?");
        return jdbcDao;
    }
}
