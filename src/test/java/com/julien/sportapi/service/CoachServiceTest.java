package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CoachServiceTest {
    CoachDao mockedDao;
    CoachService mockedService;

    @Before
    public void setUp() {
        mockedDao = mock(CoachDao.class);
        mockedService = new CoachService(mockedDao);
    }

    @Test
    public void add() {
        mockedService.add("Toto");

        ArgumentCaptor<Coach> coachArgumentCaptor = ArgumentCaptor.forClass(Coach.class);
        verify(mockedDao).add(coachArgumentCaptor.capture());

        Coach coachSentToDAO = coachArgumentCaptor.getValue();
        assertThat(coachSentToDAO.getCoachId(), is(notNullValue()));
        assertThat(coachSentToDAO.getCoachName(), is("Toto"));
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAll() {
        List<Coach> expectedCoach = Arrays.asList(
                new Coach(UUID.randomUUID(), "Super coach"),
                new Coach(UUID.randomUUID(), "Super tout court")
        );

        when(mockedDao.findAll()).thenReturn(expectedCoach);

        List<Coach> actualCoaches = mockedService.findAll();
        assertThat(actualCoaches, equalTo(expectedCoach));
    }

    @Test
    public void findByName() {
        List<Coach> CoachList = Arrays.asList(
                new Coach(UUID.randomUUID(), "Super coach"),
                new Coach(UUID.randomUUID(), "Super tout court")
        );

        Coach expectedResult = mockedDao.findByName("Super coach");

        when(expectedResult).thenReturn(CoachList
                .stream().filter(coach -> coach.getCoachName().contains("Super coach")).findFirst().get());

        Coach actualCoach = mockedService.findByName("Super coach");
        assertThat(actualCoach, equalTo(expectedResult));
    }
}