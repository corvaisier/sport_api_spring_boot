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

    public PersonDaoInMemory(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Person> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Person> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<Person> findByUserLogin(String userLogin) {
        return userRepository.findByUserLogin(userLogin);
    }

    @Override
    public void add(Person user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Person user) {
        userRepository.delete(user);
    }
}
