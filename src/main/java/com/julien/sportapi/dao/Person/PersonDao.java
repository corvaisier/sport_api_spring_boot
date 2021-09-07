package com.julien.sportapi.dao.Person;

import com.julien.sportapi.domain.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    List<Person> findAll();
    Optional<Person> findById(UUID userId);
    Optional<Person> findByUserLogin(String userLogin);
    void add(Person user);
    void delete(Person user);
    void update(Person person );
}
