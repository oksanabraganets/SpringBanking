package com.example.SpringBanking.config;

import com.example.SpringBanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/readme.txt", "/css/*", "/js/*").permitAll()
                .antMatchers("/form", "/index.html").permitAll()
                .antMatchers("/userPage.html", "/bills", "/credit").hasRole("USER")
                .antMatchers("/admin.html", "/user").hasRole("ADMIN")
                .antMatchers("/api").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder customPasswordEncoder(){
        return new ShaPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(customPasswordEncoder());
    }

}