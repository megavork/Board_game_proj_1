package com.example.Board_game_proj_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/login")
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll();
        http.authorizeRequests()
//                .antMatchers("/hello").hasRole("ADMIN")
                .anyRequest().permitAll();
/*        http.formLogin()
                //.loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/");*/
        http.logout()
                .and().logout().logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")        //удаляет куки
                .invalidateHttpSession(true);       //делает сессию Инвалидной )
        http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(120);     //время валидности токена   //сутки 86400
        http.exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf().disable();
    }
}
