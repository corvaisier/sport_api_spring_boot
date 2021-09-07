package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findByPersonName(String personName);
    Optional<Person> findByPersonLogin(String personLogin);
    Optional<Person> findPersonByCoaches(Coach coach);
}

