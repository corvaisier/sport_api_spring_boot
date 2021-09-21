package com.julien.sportapi.security;

import com.julien.sportapi.repository.CoachRepository;
import com.julien.sportapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final CoachRepository coachRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(!personRepository.findByEmail(email).isEmpty()) {
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
        if(coachRepository.findCoachByEmail(email).isPresent()){
            return coachRepository.findCoachByEmail(email)
                    .map(u -> User.builder()
                            .username(u.getEmail())
                            .password(u.getPassword())
                            .roles(u.getStatus())
                            .build())
                    .orElseThrow(() -> new UsernameNotFoundException(email));
        }
        throw new UsernameNotFoundException(email);
    }
}
