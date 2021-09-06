package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.exception.CoachException.CoachByNameNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachIdNotFoundException;

import com.julien.sportapi.repository.CoachRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CoachDaoInDB implements CoachDao{
    private final CoachRepository coachRepository;

    public CoachDaoInDB(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Coach findByName(String coachName) {
        return coachRepository.findByCoachName(coachName).orElseThrow(() -> new CoachByNameNotFoundException(coachName));
    }

    @Override
    public Coach findById(UUID coachId) {
        return coachRepository.findById(coachId).orElseThrow(() -> new CoachIdNotFoundException(coachId));
    }

    @Override
    public void add (Coach coach) {
        coachRepository.save(coach);
    }

    public void delete(UUID coachId) {
        Coach coachToDelete = coachRepository.findById(coachId).orElseThrow(() -> new CoachIdNotFoundException(coachId));
        coachRepository.delete(coachToDelete);
    }

    @Override
    public void update(UUID coachId, String newCoachName) {
        Coach currentCoach = coachRepository.findById(coachId).orElseThrow(() -> new CoachIdNotFoundException(coachId));
        currentCoach.setCoachName(newCoachName);
        coachRepository.save(currentCoach);
    }
}
