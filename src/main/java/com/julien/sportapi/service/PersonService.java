package com.julien.sportapi.service;

import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.SignUp;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonLoginNotUniqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PersonService(PersonDao userDao, PasswordEncoder passwordEncoder) {
        this.personDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Person> findAll() {
        logger.info(" *********************users************************ : {}");
        return personDao.findAll();}

    public Person findById(UUID userId) {
        return personDao.findById(userId).orElseThrow(() -> new PersonByIdNotFoundException(userId));
    }

    public void add(SignUp signUp) throws PersonLoginNotUniqException {
        if(personDao.findByUserLogin(signUp.getUserLogin()).isPresent()) {
            throw new PersonLoginNotUniqException(signUp.getUserLogin());
        }
        else {
            Person newUser = Person.builder()
                    .userId(UUID.randomUUID())
                    .userName(signUp.getUserName())
                    .userLogin(signUp.getUserLogin())
                    .userPassword(passwordEncoder.encode(signUp.getUserPassword()))
                    .userStatus(signUp.getUserStatus())
                    .build();
            personDao.add(newUser);
            logger.info("create new user : {}", newUser);
        }
    }

    public void delete (UUID userId) {
        Person userToDelete = personDao.findById(userId).orElseThrow(() -> new PersonByIdNotFoundException(userId));
        personDao.delete(userToDelete);
        logger.info("delete user : {}", userToDelete);
    }

}
