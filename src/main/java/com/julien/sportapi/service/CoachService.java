package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.AttachCoachPerson;
import com.julien.sportapi.dto.SignUpCoach;
import com.julien.sportapi.dto.UuId;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachNameNotUniqException;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Service
public class CoachService {

    private final CoachDao coachDao;
    private final PersonDao personDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public CoachService(CoachDao coachDao, PersonDao personDao) {
        this.coachDao = coachDao;
        this.personDao = personDao;
    }

    public void add(SignUpCoach signUpCoach) throws CoachNameNotUniqException {
        if(!coachDao.findByName(signUpCoach.getCoachName()).isEmpty()) {
            throw new CoachNameNotUniqException(signUpCoach.getCoachName());
        } else {
            Coach newCoach = Coach.builder()
                    .coachId(UUID.randomUUID())
                    .coachName(signUpCoach.getCoachName())
                    .persons(new ArrayList<>())
                    .build();
            coachDao.add(newCoach);
            logger.info("create new coach : {}", newCoach);
        }
    }

    public void delete(UUID coachId) throws CoachByIdNotFoundException{
        if(coachDao.findById(coachId).isPresent()) {
            coachDao.delete(coachId);
            logger.info("delete coach : {}", coachId);
        }
        else {
            throw new CoachByIdNotFoundException(coachId);
        }

    }

    public void update(Coach coach) {
        coachDao.update(coach);
        logger.info("update coach : {}", coach);
    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public List<Coach> findByName(String coachName) {
        return coachDao.findByName(coachName);
    }

    public Coach findById(UuId id) {
        return coachDao.findById(id.getId()).orElseThrow(() -> new CoachByIdNotFoundException(id.getId()));
    }

    public List<Person> findPerson(UuId id) {
        Coach coach = coachDao.findById(id.getId()).orElseThrow(() -> new CoachByIdNotFoundException(id.getId()));
        return coach.getPersons();
    }

    @Transactional
    public void attachPerson(AttachCoachPerson attachCoachPerson) {
        Coach coach = coachDao.findById(attachCoachPerson.getCoachId()).orElseThrow(() -> new CoachByIdNotFoundException(attachCoachPerson.getCoachId()));
        Person person = personDao.findById(attachCoachPerson.getPersonId()).orElseThrow(() -> new PersonByIdNotFoundException(attachCoachPerson.getPersonId()));
        coach.getPersons().add(person);
    }

}
