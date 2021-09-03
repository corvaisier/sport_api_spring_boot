package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GymRepository extends JpaRepository<Gym, UUID> {

    Optional<Gym> findByGymName(String gymName);
}
