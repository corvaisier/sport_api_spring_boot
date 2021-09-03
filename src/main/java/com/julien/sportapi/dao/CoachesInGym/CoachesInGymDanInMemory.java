package com.julien.sportapi.dao.CoachesInGym;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.repository.CoachesInGymRepository;

import java.util.List;
import java.util.UUID;

public class CoachesInGymDanInMemory implements CoachesInGymDao{
    private CoachesInGymRepository coachesInGymRepository;

    public CoachesInGymDanInMemory(CoachesInGymRepository coachesInGymRepository) {
        this.coachesInGymRepository = coachesInGymRepository;
    }

    @Override
    public List<CoachesInGymDao> findAll() {
        return null;
    }

    @Override
    public Coach findById(UUID coachesInGymDaoId) {
        return null;
    }

    @Override
    public void add(CoachesInGymDao coachesInGymDao) {

    }

    @Override
    public void delete(CoachesInGymDao coachesInGymDao) {

    }
}
