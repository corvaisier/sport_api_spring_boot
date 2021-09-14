package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.dto.coach.SignUpCoach;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachByNameNotFoundException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;


//TODO to implement !
@SpringBootTest
public class CoachServiceTest {

    @Autowired
    private CoachService coachService;
    @MockBean
    private CoachDao coachDao;

    UUID idOne = UUID.randomUUID();
    UUID idTwo = UUID.randomUUID();
    UuId id = new UuId(idOne);
    UuId randomId = new UuId(UUID.randomUUID());


    List<Coach> coachList = Arrays.asList(
            new Coach(idOne, "coachOne", "coachOne", new ArrayList<>(), new ArrayList<>()),
            new Coach(idTwo, "coachTwo", "coachTwo", new ArrayList<>(), new ArrayList<>())
    );

    @Test
    void add() {
        SignUpCoach signUpCoach = new SignUpCoach("coach", "password");
        coachService.add(signUpCoach);
        ArgumentCaptor<Coach> coachArgumentCaptor = ArgumentCaptor.forClass(Coach.class);
        verify(coachDao).add(coachArgumentCaptor.capture());

        Coach coachSentToDao = coachArgumentCaptor.getValue();
        MatcherAssert.assertThat(coachSentToDao.getId(), is(Matchers.notNullValue()));
        MatcherAssert.assertThat(coachSentToDao.getName(), is("coach"));
    }

    @Test
    void delete() {
//TODO: implement tests !!


        assertThatThrownBy(() -> coachService.delete(randomId))
                .isInstanceOf(CoachByIdNotFoundException.class)
                .hasMessage("a coach with id " + randomId.getId() + " does not exist");
    }

    @Test
    void update() {

        assertThatThrownBy(() -> coachService.update())
                .isInstanceOf(CoachByIdNotFoundException.class)
                .hasMessage("a coach with id " + randomId.getId() + " does not exist");
    }

    @Test
    void findAll() {
        when(coachDao.findAll()).thenReturn(coachList);
        MatcherAssert.assertThat(coachService.findAll(), equalTo(coachList));
    }

    @Test
    void findByName() {
        Coach expectCoach = coachList
                .stream().filter(c -> Objects.equals(c.getName(), "coachOne"))
                .findFirst().orElseThrow(() -> new CoachByNameNotFoundException("coachOne"));
        when(coachDao.findByName("coachOne")).thenReturn(expectCoach);
        when(coachDao.findByName("impossibleName")).thenThrow(new CoachByNameNotFoundException("impossibleName"));

        MatcherAssert.assertThat(coachService.findByName("coachOne"), equalTo(expectCoach));
        assertThatThrownBy(() -> coachService.findByName("impossibleName"))
                .isInstanceOf(CoachByNameNotFoundException.class)
                .hasMessage("a coach with name impossibleName does not exist");
    }

    @Test
    void findById() {
        UUID idOne = UUID.randomUUID();
        UUID idTwo = UUID.randomUUID();
        UuId idOneUuId = new UuId(idOne);
        UuId idTwoUuId = new UuId(idTwo);

        Coach coach = new Coach(idOneUuId.getId(), "coachOne", "coachOne", new ArrayList<>(), new ArrayList<>());

        when(coachDao.findById(idOne)).thenReturn(Optional.of(coach));
        when(coachDao.findById(idTwo)).thenThrow(new CoachByIdNotFoundException(idTwo));

        MatcherAssert.assertThat(coachService.findById(idOneUuId), equalTo(coach));
        assertThatThrownBy(() -> coachService.findById(idTwoUuId))
                .isInstanceOf(CoachByIdNotFoundException.class)
                .hasMessage("a coach with id " + idTwoUuId.getId() + " does not exist");
    }

    @Test
    void findPerson() {
    }

    @Test
    void attachPerson() {
    }
}