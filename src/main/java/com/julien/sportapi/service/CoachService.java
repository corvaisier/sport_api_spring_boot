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

    public void add(String coach_Name) {
        Coach newCoach = new Coach(UUID.randomUUID(), coach_Name);
        coachDao.add(newCoach);
    }

    public void delete(String coach_Name) {

    }

    public List<Coach> findAll() {
        return coachDao.findAll();
    }

    public Optional<Coach> findByName(String coach_Name) {
        return coachDao.findByName(coach_Name);
    }

}
