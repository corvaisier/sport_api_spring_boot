package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    Optional<Person> findByUserName(String userName);
    Optional<Person> findByUserLogin(String userLogin);

}
