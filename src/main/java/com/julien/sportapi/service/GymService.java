package com.julien.sportapi.service;

import com.julien.sportapi.dao.Gym.GymDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Gym;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GymService {

    private GymDao gymDao;

    public GymService(GymDao gymDao) {
        this.gymDao = gymDao;
    }

    public Gym add(String gymName, String gymLocation) {
        Gym newGym = new Gym(UUID.randomUUID(), gymName, gymLocation);
        gymDao.add(newGym);
        return newGym;
    }

    public void delete(String gymName) {
        gymDao.delete(gymName);
    }

    public void update(String gymName, String newGymName) {
        gymDao.update(gymName, newGymName);
    }

    public List<Gym> findAll() {
        return gymDao.findAll();
    }

    public Optional<Gym> findByName(String gymName) {
        return gymDao.findByName(gymName);
    }
}
