package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
public class CoachService {

    private CoachDao coachDao;
    
    public CoachService(CoachDao coachDao) {
        this.coachDao = coachDao;
    }

    public Coach add(String coachName) {
        Coach newCoach = new Coach(UUID.randomUUID(), coachName);
        coachDao.add(newCoach);
        return newCoach;
    }

    public void delete(String coachName) {
        coachDao.delete(coachName);
    }

    public void update(String coachName, String new_CoachName) {
        coachDao.update(coachName, new_CoachName);
    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public Optional<Coach> findByName(String coachName) {
        return coachDao.findByName(coachName);
    }

}
