package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.AddPersonToCoachList;
import com.julien.sportapi.dto.coach.CoachDto;
import com.julien.sportapi.dto.coach.CoachDtoForUpdate;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachByNameNotFoundException;
import com.julien.sportapi.exception.general.EntityForbiddenDeleteException;
import com.julien.sportapi.exception.CoachException.CoachNameNotUniqException;
import com.julien.sportapi.exception.CoachException.CoachPersonAlreadyExistException;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachDao coachDao;
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void add(CoachDto signUpCoach) throws CoachNameNotUniqException {
        if(coachDao.findByName(signUpCoach.getName()) != null)
            throw new CoachNameNotUniqException(signUpCoach.getName());
        else {
            Coach newCoach = Coach.builder()
                    .id(UUID.randomUUID())
                    .name(signUpCoach.getName())
                    .email(signUpCoach.getEmail())
                    .password(passwordEncoder.encode(signUpCoach.getPassword()))
                    .persons(new ArrayList<>())
                    .status("coach")
                    .build();
            coachDao.add(newCoach);
            logger.info("create new coach : {}", newCoach);
        }
    }

    public void delete(UuId id) throws EntityForbiddenDeleteException {
        Coach coachToDelete = findById(id);
        if (!coachToDelete.getStatus().equals("admin")) {
            coachDao.delete(id.getId());
            logger.info("delete coach : {}", id.getId());
        } else {
            throw new EntityForbiddenDeleteException(id);
        }
    }

    public void update(CoachDtoForUpdate coachDtoForUpdate) {
        Coach coachToUpdate = coachDao.findByName(coachDtoForUpdate.getCurrentName());
        Coach verifyCoachNameIsUniq = coachDao.findByName(coachDtoForUpdate.getNewName());

        if (coachToUpdate == null) {
            throw new CoachByNameNotFoundException(coachDtoForUpdate.getCurrentName());
        }
        if (coachDtoForUpdate.getCurrentName().equals(coachDtoForUpdate.getNewName()) && verifyCoachNameIsUniq != null) {

            coachToUpdate.setName(coachDtoForUpdate.getNewName());
            coachToUpdate.setPassword(coachDtoForUpdate.getNewPassword());
            coachDao.add(coachToUpdate);
            logger.info("update coach : {}", coachToUpdate.getName());
        } else {
            throw new CoachNameNotUniqException(coachDtoForUpdate.getNewName());
        }

    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public Coach findByName(String name) {
        return coachDao.findByName(name);
    }

    public Coach findById(UuId id) {
        return coachDao.findById(id.getId()).orElseThrow(() -> new CoachByIdNotFoundException(id.getId()));
    }

    public List<Person> findPerson(UuId id) {
        Coach coach = coachDao.findById(id.getId()).orElseThrow(() -> new CoachByIdNotFoundException(id.getId()));
        return coach.getPersons();
    }

    @Transactional
    public void attachPerson(AddPersonToCoachList addPersonToCoachList) {
        Coach coach = coachDao.findById(addPersonToCoachList.getCoachId()).orElseThrow(() -> new CoachByIdNotFoundException(addPersonToCoachList.getCoachId()));
        Person person = personDao.findById(addPersonToCoachList.getPersonId()).orElseThrow(() -> new PersonByIdNotFoundException(addPersonToCoachList.getPersonId()));
        if(coach.getPersons().stream().anyMatch(c -> c.getId() == person.getId())) {
            throw new CoachPersonAlreadyExistException(person.getId());
        }
        coach.getPersons().add(person);
    }

}
