package com.julien.sportapi.service;

import com.julien.sportapi.dao.Coach.CoachDao;
import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.AddPersonToCoachList;
import com.julien.sportapi.dto.coach.CoachDto;
import com.julien.sportapi.dto.coach.CoachDtoForUpdate;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.exception.CoachException.CoachByIdNotFoundException;
import com.julien.sportapi.exception.CoachException.CoachByNameNotFoundException;
import com.julien.sportapi.exception.general.EntityForbiddenDeleteException;
import com.julien.sportapi.exception.CoachException.CoachPersonAlreadyExistException;
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

@SpringBootTest
public class CoachServiceTest {

    @Autowired
    private CoachService coachService;
    @MockBean
    private CoachDao coachDao;
    @MockBean
    private PersonDao personDao;

    private final UUID idOne = UUID.randomUUID();
    private final UUID idTwo = UUID.randomUUID();
    private final UuId uuIdOne = new UuId(idOne);
    private final UuId uuIdTwo = new UuId(idTwo);

    private final List<Coach> coachList = Arrays.asList(
            new Coach(idOne, "coachOne", "coachOne@gmail.com", "coach", "coach", new ArrayList<>(), new ArrayList<>()),
            new Coach(idTwo, "coachTwo","coachTwo@gmail.com", "coachTwo", "admin", new ArrayList<>(), new ArrayList<>())
    );
    private final List<Person> personList = Arrays.asList(
            new Person(idOne, "name", "firstName", "email@email.com", "password", "customer", new ArrayList<>(), new ArrayList<>()),
            new Person(idTwo, "otherName", "firstName", "email@email.com", "password", "customer", new ArrayList<>(), new ArrayList<>())
    );

    @Test
    void add() {
        CoachDto signUpCoach = new CoachDto("coach", "coach@gmail.com", "coach");
        coachService.add(signUpCoach);
        ArgumentCaptor<Coach> coachArgumentCaptor = ArgumentCaptor.forClass(Coach.class);
        verify(coachDao).add(coachArgumentCaptor.capture());

        Coach coachSentToDao = coachArgumentCaptor.getValue();
        MatcherAssert.assertThat(coachSentToDao.getId(), is(Matchers.notNullValue()));
        MatcherAssert.assertThat(coachSentToDao.getName(), is("coach"));
    }

    @Test
    void delete() {
        Coach coachOne =  coachList.get(0);
        Optional<Coach> optionalCoach = Optional.of(coachOne);
        when (coachDao.findById(idOne)).thenReturn(optionalCoach);
        doNothing().when(coachDao).delete(idOne);

        coachService.delete(uuIdOne);
        verify(coachDao).delete(idOne);

        Coach coachTwo =  coachList.get(1);
        Optional<Coach> optionalCoachTwo = Optional.of(coachTwo);
        when (coachDao.findById(idTwo)).thenReturn(optionalCoachTwo);
        doNothing().when(coachDao).delete(idTwo);

        assertThatThrownBy(() -> coachService.delete(uuIdTwo))
                .isInstanceOf(EntityForbiddenDeleteException.class)
                .hasMessage("This entity: " + uuIdTwo + " can't be changed ! ");
    }

    @Test
    void update() {
        Coach coachOne =  coachList.get(0);

        CoachDtoForUpdate coachForUpdate = new CoachDtoForUpdate("coachOne", "coachWoaou", "coachOne@gmail.com", "coachWoaou@gmail.com", "coach", "coachWoaou");
        when(coachDao.findCoachByEmail(coachForUpdate.getCurrentEmail())).thenReturn(Optional.ofNullable(coachOne));
        doNothing().when(coachDao).add(Optional.ofNullable(coachOne).get());

        coachService.update(coachForUpdate);
        verify(coachDao).add(coachOne);
    }

    @Test
    void findAll() {
        when(coachDao.findAll()).thenReturn(coachList);
        MatcherAssert.assertThat(coachService.findAll(), equalTo(coachList));
    }

    @Test
    void findCoachByName() {
        Coach expectCoach = coachList
                .stream().filter(c -> Objects.equals(c.getName(), "coachOne"))
                .findFirst().orElseThrow(() -> new CoachByNameNotFoundException("coachOne"));
        when(coachDao.findCoachByName("coachOne")).thenReturn(Optional.ofNullable(expectCoach));
        when(coachDao.findCoachByName("impossibleName")).thenThrow(new CoachByNameNotFoundException("impossibleName"));

        MatcherAssert.assertThat(coachService.findCoachByName("coachOne"), equalTo(expectCoach));
        assertThatThrownBy(() -> coachService.findCoachByName("impossibleName"))
                .isInstanceOf(CoachByNameNotFoundException.class)
                .hasMessage("a coach with name impossibleName does not exist");
    }

    @Test
    void findById() {
        UuId randomId = new UuId(UUID.randomUUID());
        
        when(coachDao.findById(idOne)).thenReturn(Optional.of(coachList.get(0)));
        when(coachDao.findById(idTwo)).thenThrow(new CoachByIdNotFoundException(idTwo));

        MatcherAssert.assertThat(coachService.findById(uuIdOne), equalTo(coachList.get(0)));
        assertThatThrownBy(() -> coachService.findById(randomId))
                .isInstanceOf(CoachByIdNotFoundException.class)
                .hasMessage("a coach with id " + randomId.getId() + " does not exist");
    }

    @Test
    void findPerson() {
        coachList.get(0).getPersons().add(personList.get(0));
        Optional<Coach> optionalCoach = Optional.of(coachList.get(0));
        when(coachDao.findById(idOne)).thenReturn(optionalCoach);
        MatcherAssert.assertThat(coachService.findPerson(uuIdOne).size(), equalTo(1));
        MatcherAssert.assertThat(coachService.findPerson(uuIdOne).get(0).getName(), equalTo("name"));
    }

    @Test
    void attachPerson() {
        coachList.get(0).getPersons().add(personList.get(1));
        System.out.println(coachList.get(0).getPersons());
        AddPersonToCoachList addPersonToCoachList = new AddPersonToCoachList(idOne, idTwo);

        Optional<Coach> optionalCoach = Optional.of(coachList.get(0));
        when(coachDao.findById(idOne)).thenReturn(optionalCoach);

        Optional<Person> optionalPerson = Optional.of(personList.get(1));
        when(personDao.findById(idTwo)).thenReturn(optionalPerson);

        assertThatThrownBy(() -> coachService.attachPerson(addPersonToCoachList))
                .isInstanceOf(CoachPersonAlreadyExistException.class)
                .hasMessage("This person: " + idTwo + " already exist ! ");
    }
}