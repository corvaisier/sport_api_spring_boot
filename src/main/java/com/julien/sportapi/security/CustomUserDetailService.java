package com.julien.sportapi.security;

import com.julien.sportapi.repository.PersonRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final PersonRepository userRepository;

    public CustomUserDetailService(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        return userRepository.findByUserLogin(userLogin)
                .map(u -> User.builder()
                                .username(u.getUserLogin())
                                .password(u.getUserPassword())
                                .roles(u.getUserStatus())
                                .build())
                .orElseThrow(() -> new UsernameNotFoundException(userLogin));

    }
}
