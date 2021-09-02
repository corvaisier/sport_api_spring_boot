package com.julien.sportapi.dao.Coach;

import com.julien.sportapi.domain.Coach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CoachDaoInH2Test {

    private CoachDao coachDao;
    private Coach firstCoach;
    private Coach secondCoach;

    @BeforeEach
    void setUp() {
        coachDao = new CoachDaoInH2();
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