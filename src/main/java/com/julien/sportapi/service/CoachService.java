package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
public class CoachService {

    private final CoachDao coachDao;
    
    public CoachService(CoachDao coachDao) {
        this.coachDao = coachDao;
    }

    public Coach add(String coachName) {
        Coach newCoach = new Coach(UUID.randomUUID(), coachName);
        coachDao.add(newCoach);
        return newCoach;
    }

    public void delete(Coach coach) {
        coachDao.delete(coach);
    }

    public void update(String coachName, String newCoachName) {
        coachDao.update(coachName, newCoachName);
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
