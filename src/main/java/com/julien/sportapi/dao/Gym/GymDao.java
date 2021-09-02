package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Gym;

import java.util.List;
import java.util.Optional;

public interface GymDao {
    List<Gym> findAll();
    Optional<Gym> findByName(String gymName);
    void add(Gym gym);
    void delete(String gymName);
    void update(String gymName, String newGymName);
}
