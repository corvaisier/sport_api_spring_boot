package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CoachRepository extends JpaRepository<Coach, UUID> {

    List<Coach> findByName(String Name);
}
