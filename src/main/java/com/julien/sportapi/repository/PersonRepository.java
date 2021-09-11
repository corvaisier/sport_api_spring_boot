package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findByEmail(String email);
    List<Person> findByName(String name);

}

