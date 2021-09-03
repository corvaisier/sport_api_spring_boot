package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;

class CoachDaoInDBTest {

    private CoachDao coachDao;
    private Coach firstCoach;
    private Coach secondCoach;

    @BeforeEach
    void setUp() {
        coachDao = new CoachDaoInDB(null);
        firstCoach = new Coach(UUID.randomUUID(), "firstCoach");
        secondCoach = new Coach(UUID.randomUUID(), "secondCoach");

        coachDao.add(firstCoach);
        coachDao.add(secondCoach);
    }

    @Test
    Coach findByCoachName() {

        return null;
    }

    @Test
    void update() {

    }
}