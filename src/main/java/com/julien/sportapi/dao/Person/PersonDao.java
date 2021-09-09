package com.julien.sportapi.dao.Person;

import com.julien.sportapi.domain.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    List<Person> findAll();
    Optional<Person> findById(UUID id);
    Optional<Person> findByUserName(String name);
    void add(Person person);
    void delete(Person person);
    void update(Person person );
}
