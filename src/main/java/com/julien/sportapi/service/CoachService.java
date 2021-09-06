package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Service
public class CoachService {

    private final CoachDao coachDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public CoachService(CoachDao coachDao) {
        this.coachDao = coachDao;
    }

    public Coach add(String coachName) {
        Coach newCoach = new Coach(UUID.randomUUID(), coachName /*, new ArrayList<>()*/);
        logger.info("create new coach : {}", newCoach);
        coachDao.add(newCoach);
        return newCoach;
    }

    public void delete(UUID coachId) {
        Coach coachToDelete = coachDao.findById(coachId);
        coachDao.delete(coachId);
        logger.info("delete coach : {}", coachToDelete);
    }

    public void update(UUID coachId, String newCoachName) {
        coachDao.update(coachId, newCoachName);
        logger.info("update coach : {}", coachId, newCoachName);
    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public Coach findByName(String coachName) {
        return coachDao.findByName(coachName);
    }

    public Coach findById(UUID coachId) {
        return coachDao.findById(coachId);
    }
}
