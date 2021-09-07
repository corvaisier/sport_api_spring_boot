package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
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

    public Coach add(String coachName) throws CoachNameNotUniqException {
        if(coachDao.findByName(coachName).isEmpty()) {
            throw new CoachNameNotUniqException(coachName);
        } else {
            Coach newCoach = new Coach(UUID.randomUUID(), coachName, new ArrayList<>());
            coachDao.add(newCoach);
            logger.info("create new coach : {}", newCoach);
            return newCoach;
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

    public Coach findById(UUID coachId) {
        return coachDao.findById(coachId).orElseThrow(() -> new CoachByIdNotFoundException(coachId));
    }

    @Transactional
    public void attachPerson(UUID personId, UUID coachId) {
        Coach coach = coachDao.findById(coachId).orElseThrow(() -> new CoachByIdNotFoundException(coachId));
        Person person = personDao.findById(personId).orElseThrow(() -> new PersonByIdNotFoundException(personId));
        coach.getPersons().add(person);
    }

}
