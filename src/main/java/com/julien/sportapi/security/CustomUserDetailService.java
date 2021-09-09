package com.julien.sportapi.security;

import com.julien.sportapi.repository.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final PersonRepository personRepository;

    public CustomUserDetailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
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
