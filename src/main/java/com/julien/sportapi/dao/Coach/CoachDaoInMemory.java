package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CoachDaoInMemory implements CoachDao {
    private List<Coach> coachs = new ArrayList<>();

    public List<Coach> findAll() {
        return coachs;
    }

    public Optional<Coach> findByName(String coach_Name) {
        return coachs.stream().filter(coach -> coach.getCoach_Name().contains(coach_Name)).findFirst();
    }

    public void add(Coach coach) {
        coachs.add(coach);
    }

    public void delete(String coach_Name) {
    }

}
