package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Gym;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GymDaoInMemory implements GymDao {
    private List<Gym> gyms = new ArrayList<>();

    public List<Gym> findAll() {
        return gyms;
    }

    public Optional<Gym> findByName(String coachName) {
        return gyms.stream().filter(coach -> coach.getGymName().contains(coachName)).findFirst();
    }

    public void add(Gym gym) {
        gyms.add(gym);
    }

    public void delete(String gymName) {
        gyms.removeIf(next -> next.getGymName().equals(gymName));
    }

    public void update(String gymName, String newGymName) {
        for (Gym next : gyms) {
            if (next.getGymName().equals(gymName)) {
                next.setGymName(newGymName);
            }
        }
    }
}
