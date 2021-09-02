package com.julien.sportapi.service;

import com.julien.sportapi.dao.Gym.GymDao;
import com.julien.sportapi.domain.Gym;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GymService {

    private final GymDao gymDao;

    public GymService(GymDao gymDao) {
        this.gymDao = gymDao;
    }

    public Gym add(String gymName, String gymLocation) {
        Gym newGym = new Gym(UUID.randomUUID(), gymName, gymLocation);
        gymDao.add(newGym);
        return newGym;
    }

    public void delete(Gym gym) {
        gymDao.delete(gym);
    }

    public void update(Gym newGym) {
//        Gym currentGym = gymDao.findById(newGym.getGymID()).orElseThrow(()-> new RuntimeException());
//        if(!newGym.getGymName().equals(null)) currentGym.setGymName(newGym.getGymName());
//        if(!newGym.getGymLocation().equals(null)) currentGym.setGymLocation(newGym.getGymLocation());
//        gymDao.update(currentGym);
    }

    public List<Gym> findAll() {
        return gymDao.findAll();
    }

    public Gym findByName(String gymName) {
        return gymDao.findByGymName(gymName);
    }
}
