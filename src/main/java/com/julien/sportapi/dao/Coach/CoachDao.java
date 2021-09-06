package com.julien.sportapi.dao.Coach;

import java.util.List;
import java.util.UUID;

import com.julien.sportapi.domain.Coach;

public interface CoachDao {
    List<Coach> findAll();
    Coach findByName(String coachName);
    Coach findById(UUID coachId);
    void add(Coach coach);
    void delete(UUID coachId);
    void update(UUID coachId, String newCoachName);
}
