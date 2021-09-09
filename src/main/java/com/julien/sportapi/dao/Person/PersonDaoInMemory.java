package com.julien.sportapi.dao.Person;

import com.julien.sportapi.domain.Person;
import com.julien.sportapi.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonDaoInMemory implements PersonDao {
    private final PersonRepository userRepository;

    public PersonDaoInMemory(PersonRepository personRepository) {
        this.userRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return userRepository.findByPersonName(name);
    }

    @Override
    public void add(Person person) {
        userRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        userRepository.delete(person);
    }

    @Override
    public void update(Person person) {
        userRepository.save(person);
    }
}
