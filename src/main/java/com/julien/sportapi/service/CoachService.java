package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.AddPersonToCoachList;
import com.julien.sportapi.dto.coach.CoachDto;
import com.julien.sportapi.dto.coach.CoachDtoForUpdate;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachByNameNotFoundException;
import com.julien.sportapi.exception.general.EntityForbiddenDeleteException;
import com.julien.sportapi.exception.CoachException.CoachEmailNotUniqException;
import com.julien.sportapi.exception.CoachException.CoachPersonAlreadyExistException;
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
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void add(CoachDto signUpCoach) throws CoachEmailNotUniqException {
            try {
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
            } catch (Exception e) {
                    throw new CoachEmailNotUniqException(signUpCoach.getEmail());
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
        Coach coachToUpdate = findCoachByEmail(coachDtoForUpdate.getCurrentEmail());
        coachToUpdate.setName(coachDtoForUpdate.getNewName());
        coachToUpdate.setEmail(coachDtoForUpdate.getNewEmail());
        coachToUpdate.setPassword(coachDtoForUpdate.getNewPassword());
        coachDao.add(coachToUpdate);
        logger.info("update coach : {}", coachToUpdate.getName());
    }

    public Coach findCoachByEmail(String email) {
        return coachDao.findCoachByEmail(email).orElseThrow(() -> new CoachEmailNotUniqException(email));
    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public Coach findCoachByName(String name) {
        return coachDao.findCoachByName(name).orElseThrow(() -> new CoachByNameNotFoundException(name));
    }

    public Coach findById(UuId id) {
        return coachDao.findById(id.getId()).orElseThrow(() -> new CoachByIdNotFoundException(id.getId()));
    }

    public List<Person> findPerson(UuId id) {
        Coach coach = findById(id);
        return coach.getPersons();
    }

    @Transactional
    public void attachPerson(AddPersonToCoachList addPersonToCoachList) {
        UuId coachId = new UuId(addPersonToCoachList.getCoachId());
        UuId personId = new UuId(addPersonToCoachList.getPersonId());
        Coach coach = findById(coachId);
        Person person = personService.findById(personId);
        if(coach.getPersons().stream().anyMatch(c -> c.getId() == person.getId())) {
            throw new CoachPersonAlreadyExistException(person.getId());
        }
        coach.getPersons().add(person);
    }

}
