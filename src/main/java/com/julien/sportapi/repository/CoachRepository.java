package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoachRepository extends JpaRepository<Coach, UUID> {
    Optional<Coach> findCoachByName(String name);
    Optional<Coach> findCoachByEmail(String email);
}
