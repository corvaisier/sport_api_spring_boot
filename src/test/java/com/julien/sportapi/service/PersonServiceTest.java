package com.julien.sportapi.service;

import com.julien.sportapi.dao.Person.PersonDao;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.person.PersonDto;
import com.julien.sportapi.dto.person.PersonDtoForUpdate;
import com.julien.sportapi.exception.general.EntityForbiddenDeleteException;
import com.julien.sportapi.exception.PersonException.PersonByIdNotFoundException;
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
class PersonServiceTest {

    @Autowired
    private PersonService personService;
    @MockBean
    private PersonDao personDao;

    private final UUID idOne = UUID.randomUUID();
    private final UUID idTwo = UUID.randomUUID();
    private final UuId uuIdOne = new UuId(idOne);
    private final UuId uuIdTwo = new UuId(idTwo);

    private final List<Person> personList = Arrays.asList(
            new Person(idOne, "name", "firstName", "email@email.com", "password", "customer", new ArrayList<>(), new ArrayList<>()),
            new Person(idTwo, "otherName", "firstName", "email@email.com", "password", "admin", new ArrayList<>(), new ArrayList<>())
    );

    @Test
    void findAll() {
        when(personDao.findAll()).thenReturn(personList);
        MatcherAssert.assertThat(personService.findAll(), equalTo(personList));
    }

    @Test
    void findById() {
        when(personDao.findById(idOne)).thenReturn(Optional.of(personList.get(0)));
        when(personDao.findById(idTwo)).thenThrow(new PersonByIdNotFoundException(idTwo));

        MatcherAssert.assertThat(personService.findById(uuIdOne), equalTo(personList.get(0)));
        assertThatThrownBy(() -> personService.findById(uuIdTwo))
                .isInstanceOf(PersonByIdNotFoundException.class)
                .hasMessage("This user id does not exist: " + idTwo);
    }

    @Test
    void add() {
        PersonDto signUpPerson = new PersonDto("person", "firstName", "email@email.com", "password");
        personService.add(signUpPerson);
        ArgumentCaptor<Person> coachArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personDao).add(coachArgumentCaptor.capture());

        Person personSentToDao = coachArgumentCaptor.getValue();
        MatcherAssert.assertThat(personSentToDao.getId(), is(Matchers.notNullValue()));
        MatcherAssert.assertThat(personSentToDao.getName(), is("person"));
    }

    @Test
    void update() {
        Person personOne = personList.get(0);

        PersonDtoForUpdate personDtoForUpdate = new PersonDtoForUpdate("name", "firstName", "email@email.com", "email@email.com", "baba", "baba" );

        when(personDao.findByEmail(personDtoForUpdate.getCurrentEmail())).thenReturn(Collections.singletonList(personOne));
        doNothing().when(personDao).add(personOne);

        personService.update(personDtoForUpdate);
        verify(personDao).add(personOne);
    }

    @Test
    void delete() {
        Person personOne =  personList.get(0);
        Optional<Person> optionalPerson = Optional.of(personOne);
        when (personDao.findById(idOne)).thenReturn(optionalPerson);
        doNothing().when(personDao).delete(optionalPerson.get());

        personService.delete(uuIdOne);
        verify(personDao).delete(personOne);

        Person personTwo =  personList.get(1);
        Optional<Person> optionalPersonTwo = Optional.of(personTwo);
        when (personDao.findById(idTwo)).thenReturn(optionalPersonTwo);
        doNothing().when(personDao).delete(optionalPersonTwo.get());

        assertThatThrownBy(() -> personService.delete(uuIdTwo))
                .isInstanceOf(EntityForbiddenDeleteException.class)
                .hasMessage("This entity: " + uuIdTwo + " can't be changed ! ");
    }
}