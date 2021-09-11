package com.julien.sportapi.dao.Person;

import com.julien.sportapi.domain.Person;
import com.julien.sportapi.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonDaoInDb implements PersonDao {
    private final PersonRepository personRepository;

    public PersonDaoInDb(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public void add(Person person) {
        personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

}
