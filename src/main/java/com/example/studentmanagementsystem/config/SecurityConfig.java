package com.example.studentmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


import javax.sql.DataSource;

@Deprecated
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


//    @Autowired
//    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
//                .withUser(
//                    User.withUsername("admin").password("admin").roles("ADMIN")
//                )
//                .withUser(
//                        User.withUsername("dax").password("dax").roles("USER")
//                );


        //In memory authentication
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and().withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}

