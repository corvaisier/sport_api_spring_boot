package com.julien.sportapi.dao.Gym;



import com.julien.sportapi.domain.Gym;

import java.util.List;

public interface GymDao {
    List<Gym> findAll();

    void add(Gym gym);
}
