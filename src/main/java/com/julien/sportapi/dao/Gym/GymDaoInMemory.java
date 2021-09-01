package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Gym;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GymDaoInMemory implements GymDao {
    private List<Gym> gyms = new ArrayList<>();

    public List<Gym> findAll() {
        return gyms;
    }

    public void add(Gym gym) {
        gyms.add(gym);
    }
}
