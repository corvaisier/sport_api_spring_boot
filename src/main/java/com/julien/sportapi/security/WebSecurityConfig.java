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
                .antMatchers("/person/**").hasAnyRole("person", "admin")
                .antMatchers("/coach/**").hasAnyRole("coach", "admin")
                .antMatchers("/administrator/**").hasRole("admin")
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin();
    }
}
