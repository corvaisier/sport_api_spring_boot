package com.julien.sportapi.dao.CoachesInGym;

import com.julien.sportapi.domain.Coach;

import java.util.List;
import java.util.UUID;

public interface CoachesInGymDao {
    List<CoachesInGymDao> findAll();
    Coach findById(UUID coachesInGymDaoId);
    void add(CoachesInGymDao coachesInGymDao);
    void delete(CoachesInGymDao coachesInGymDao);
}
