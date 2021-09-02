package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymRepository extends JpaRepository<Gym, UUID> {

    Gym findByGymName(String gymName);
}
