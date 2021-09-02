package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CoachDaoInMemory implements CoachDao {
    private final List<Coach> coaches = new ArrayList<>();

    public List<Coach> findAll() {
        return coaches;
    }

    public Optional<Coach> findByName(String coachName) {
        return coaches.stream().filter(coach -> coach.getCoachName().contains(coachName)).findFirst();
    }

    public void add(Coach coach) {
        coaches.add(coach);
    }

    public void delete(String coachName) {
        coaches.removeIf(next -> next.getCoachName().equals(coachName));
    }

    public void update(String coachName, String newCoachName) {
        for (Coach next : coaches) {
            if (next.getCoachName().equals(coachName)) {
                next.setCoachName(newCoachName);
            }
        }
    }

}
