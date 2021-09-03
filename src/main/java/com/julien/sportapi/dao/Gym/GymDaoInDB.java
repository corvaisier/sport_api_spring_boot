package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Gym;
import com.julien.sportapi.exception.GymException.GymByIdNotFoundException;
import com.julien.sportapi.exception.GymException.GymByNameNotFoundException;
import com.julien.sportapi.repository.GymRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class GymDaoInDB implements GymDao{
    private GymRepository gymRepository;

    public GymDaoInDB(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @Override
    public Gym findByGymName(String gymName) {
        return gymRepository.findByGymName(gymName).orElseThrow(() -> new GymByNameNotFoundException(gymName));
    }

    @Override
    public Gym findByGymId(UUID gymId) {
        return gymRepository.findById(gymId).orElseThrow(() -> new GymByIdNotFoundException(gymId));
    }

    @Override
    public void add(Gym gym) {
        gymRepository.save(gym);
    }

    @Override
    public void delete(Gym gym) {
        gymRepository.delete(gym);
    }

    @Override
    public void update(Gym gym) {
        Gym currentGym = gymRepository.getById(gym.getGymID());
        currentGym.setGymName(gym.getGymName());
        currentGym.setGymLocation(gym.getGymLocation());
        gymRepository.save(currentGym);
    }
}
