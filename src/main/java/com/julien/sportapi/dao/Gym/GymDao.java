package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Gym;

import java.util.List;
import java.util.UUID;

public interface GymDao {
    List<Gym> findAll();
    Gym findByGymName(String gymName);
    Gym findByGymId(UUID gymId);
    void add(Gym gym);
    void delete(Gym gym);
    void update(Gym gym);
}
