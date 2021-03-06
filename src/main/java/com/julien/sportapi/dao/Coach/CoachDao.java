package com.julien.sportapi.dao.Coach;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;

public interface CoachDao {
    List<Coach> findAll();
    Optional<Coach> findCoachByName(String coachName);
    Optional<Coach> findCoachByEmail(String coachEmail);
    Optional<Coach> findById(UUID coachId);
    void add(Coach coach);
    void delete(UUID coachId);
}
