package com.julien.sportapi.dao.Gym;

import com.julien.sportapi.domain.Gym;
import com.julien.sportapi.repository.GymRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@NoArgsConstructor
public class GymDaoInH2 implements GymDao{
    private GymRepository gymRepository;

    public GymDaoInH2(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Override
    public List<Gym> findAll() {
        return gymRepository.findAll();
    }

    @Override
    public Gym findByGymName(String gymName) {
        return gymRepository.findByGymName(gymName);
    }

    @Override
    public Gym findByGymId(UUID gymId) {
        return gymRepository.findById(gymId).orElseThrow(RuntimeException::new);
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
