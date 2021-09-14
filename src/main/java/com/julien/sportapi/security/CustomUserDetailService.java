package com.julien.sportapi.security;

import com.julien.sportapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("try to connect  ***********************************************************", email);

        return personRepository.findByEmail(email)
                .stream()
                .map(u -> User.builder()
                                .username(u.getEmail())
                                .password(u.getPassword())
                                .roles(u.getStatus())
                                .build())
                                .findFirst()
                                 .orElseThrow(() -> new UsernameNotFoundException(email));

    }
}
