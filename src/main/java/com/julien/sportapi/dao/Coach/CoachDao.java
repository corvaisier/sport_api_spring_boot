package com.julien.sportapi.dao.Coach;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.julien.sportapi.domain.Coach;

public interface CoachDao {
    List<Coach> findAll();
    List<Coach> findByName(String coachName);
    Optional<Coach> findById(UUID coachId);
    void add(Coach coach);
    void delete(UUID coachId);
    void update(Coach coach);
}
