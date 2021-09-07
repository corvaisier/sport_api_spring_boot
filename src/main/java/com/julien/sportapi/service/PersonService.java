package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.SignUp;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonLoginNotUniqException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void add(SignUp signUp) throws PersonLoginNotUniqException {
        if(personDao.findByUserLogin(signUp.getUserLogin()).isPresent()) {
            throw new PersonLoginNotUniqException(signUp.getUserLogin());
        }
        else {
            Person newPerson = Person.builder()
                    .personId(UUID.randomUUID())
                    .personName(signUp.getUserName())
                    .personLogin(signUp.getUserLogin())
                    .personPassword(passwordEncoder.encode(signUp.getUserPassword()))
                    .personStatus(signUp.getUserStatus())
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
