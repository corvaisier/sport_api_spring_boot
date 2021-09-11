package com.julien.sportapi.dao.Person;

import com.julien.sportapi.domain.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    List<Person> findAll();
    Optional<Person> findById(UUID id);
    List<Person> findByName(String name);
    List<Person> findByEmail(String email);
    void add(Person person);
    void delete(Person person);
}
