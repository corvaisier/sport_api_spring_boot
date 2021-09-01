package com.julien.sportapi.dao.Coach;

import java.util.List;
import java.util.Optional;

import com.julien.sportapi.domain.Coach;

public interface CoachDao {
    List<Coach> findAll();
    Optional<Coach> findByName(String coach_Name);
    void add(Coach coach);
    void delete(String coach_Name);
}
