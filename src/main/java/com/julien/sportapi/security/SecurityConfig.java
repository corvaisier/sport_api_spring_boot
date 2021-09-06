package com.julien.sportapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userLambda = User.builder()
                .username("userLambda")
                .password(passwordEncoder.encode("root"))
                .authorities("USER")
                .build();
        UserDetails userAdmin = User.builder()
                .username("userAdmin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(userLambda, userAdmin);
    }
}
