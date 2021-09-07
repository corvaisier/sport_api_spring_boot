package com.julien.sportapi.service;

import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.SignUpPerson;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonLoginNotUniqException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());



    public List<Person> findAll() {
        return personDao.findAll();}

    public Person findById(UUID personId) {
        return personDao.findById(personId).orElseThrow(() -> new PersonByIdNotFoundException(personId));
    }

    public void add(SignUpPerson signUpPerson) throws PersonLoginNotUniqException {
        if(personDao.findByUserLogin(signUpPerson.getPersonLogin()).isPresent()) {
            throw new PersonLoginNotUniqException(signUpPerson.getPersonLogin());
        }
        else {
            Person newPerson = Person.builder()
                    .personId(UUID.randomUUID())
                    .personName(signUpPerson.getPersonName())
                    .personLogin(signUpPerson.getPersonLogin())
                    .personPassword(passwordEncoder.encode(signUpPerson.getPersonPassword()))
                    .personStatus(signUpPerson.getPersonStatus())
                    .coaches(new ArrayList<>())
                    .build();
            personDao.add(newPerson);
            logger.info("create new user : {}", newPerson);
        }
    }

    public void delete (UUID personId) {
        Person userToDelete = personDao.findById(personId).orElseThrow(() -> new PersonByIdNotFoundException(personId));
        personDao.delete(userToDelete);
        logger.info("delete user : {}", userToDelete);
    }
}
