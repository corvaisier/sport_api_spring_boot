package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.repository.CoachRepository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@NoArgsConstructor
public class CoachDaoInH2 implements CoachDao{
    private CoachRepository coachRepository;

    public CoachDaoInH2(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Coach findByName(String coachName) {
        return coachRepository.findByCoachName(coachName);
    }

    @Override
    public Coach findById(UUID coachId) {
        return coachRepository.findById(coachId).orElseThrow(RuntimeException::new);
    }

    @Override
    public void add(Coach coach) {
        coachRepository.save(coach);
    }

    public void delete(Coach coach) {
        coachRepository.delete(coach);
    }

    @Override
    public void update(String coachName, String newCoachName) {
        Coach currentCoach = coachRepository.findByCoachName(coachName);
        currentCoach.setCoachName(newCoachName);
        coachRepository.save(currentCoach);
    }
}
