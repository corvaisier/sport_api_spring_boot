package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;

import com.julien.sportapi.repository.CoachRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public Coach findByName(String name) {
        return coachRepository.findByName(name);
    }

    @Override
    public Optional<Coach> findById(UUID coachId) {
        return coachRepository.findById(coachId);
    }


    @Override
    public void add (Coach coach) {
        coachRepository.save(coach);
    }

    public void delete(UUID coachId) {
        Coach coachToDelete = coachRepository.findById(coachId).orElseThrow(() -> new CoachByIdNotFoundException(coachId));
        coachRepository.delete(coachToDelete);
    }
}
