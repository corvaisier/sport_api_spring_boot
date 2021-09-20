package com.julien.sportapi.service;

import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.person.PersonDto;
import com.julien.sportapi.dto.person.PersonDtoForUpdate;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonByEmailNotFoundException;
import com.julien.sportapi.exception.general.EntityForbiddenDeleteException;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import com.julien.sportapi.exception.PersonException.PersonLoginNotUniqException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<Person> findAll() {
        return personDao.findAll();}

    public Person findById(UuId personId) {
        return personDao.findById(personId.getId()).orElseThrow(() -> new PersonByIdNotFoundException(personId.getId()));
    }

    public void add(PersonDto signUpPerson) throws PersonLoginNotUniqException {
        if(!personDao.findByEmail(signUpPerson.getEmail()).isEmpty()) {
            throw new PersonLoginNotUniqException(signUpPerson.getEmail());
        }
        else {
            Person newPerson = Person.builder()
                    .id(UUID.randomUUID())
                    .name(signUpPerson.getName())
                    .firstName(signUpPerson.getFirstName())
                    .email(signUpPerson.getEmail())
                    .password(passwordEncoder.encode(signUpPerson.getPassword()))
                    .status("person")
                    .coaches(new ArrayList<>())
                    .build();
            personDao.add(newPerson);
            logger.info("create new user : {}", newPerson);
        }
    }

    public void update(PersonDtoForUpdate personDtoForUpdate) {

        Person personToUpdate = personDao.findByEmail(personDtoForUpdate.getCurrentEmail()).get(0);

        personToUpdate.setName(personDtoForUpdate.getName());
        personToUpdate.setFirstName(personDtoForUpdate.getFirstName());
        personToUpdate.setEmail(personDtoForUpdate.getNewEmail());
        personToUpdate.setPassword(personDtoForUpdate.getNewPassword());

        personDao.add(personToUpdate);
        logger.info("update user : {}", personToUpdate.getEmail());
    }

    public void delete (UuId id) {
        Person personToDelete = findById(id);
        if (!personToDelete.getStatus().equals("admin")) {
            personDao.delete(personToDelete);
            logger.info("delete user : {}", personToDelete.getEmail());
        } else {
            throw new EntityForbiddenDeleteException(id);
        }
    }
}
