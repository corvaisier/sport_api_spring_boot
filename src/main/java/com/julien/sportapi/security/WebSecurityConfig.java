package com.julien.sportapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/prospect/**").permitAll()
                .antMatchers("/person/**").hasRole("person")
                //why ?? 
                .antMatchers("/coach").hasRole("coach")
                .antMatchers("/admin/**").hasRole("admin")
//                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .csrf()
                .disable();
    }
}
