package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoachRepository extends JpaRepository<Coach, UUID> {

    Coach findByCoachName(String coachName);

}
