package com.julien.sportapi.service;

import com.julien.sportapi.dao.Lesson.LessonDao;
import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.lesson.AddNewLesson;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SpringBootTest
class LessonServiceTest {

    @Autowired
    private LessonService lessonService;
    @MockBean
    private LessonDao lessonDao;

    private final UUID idOne = UUID.randomUUID();
    private final UUID idTwo = UUID.randomUUID();
    private final UuId uuIdOne = new UuId(idOne);
    private final UuId uuIdTwo = new UuId(idTwo);

    private final Coach coach = new Coach(idOne, "coachOne", "coachOne", "coach", new ArrayList<>(), new ArrayList<>());
    private final List<Lesson> lessonList = Arrays.asList(
            new Lesson(idOne, "monday", "hour", "kevin", "difficulty", new ArrayList<>(), coach),
            new Lesson(idTwo, "day", "14", "name", "simple", new ArrayList<>(), coach)
    );

    @Test
    void findAll() {
        when(lessonDao.findAll()).thenReturn(lessonList);
        MatcherAssert.assertThat(lessonService.findAll(), equalTo(lessonList));
    }

    @Test
    void findByName() {
        when(lessonDao.findByName("kevin")).thenReturn(Collections.singletonList(lessonList.get(0)));
        when(lessonDao.findByName("impossibleName")).thenReturn(new ArrayList<>());

        MatcherAssert.assertThat(lessonService.findByName("kevin").get(0).getName(), equalTo("kevin"));
        MatcherAssert.assertThat(lessonService.findByName("kevin").size(), equalTo(1));
        MatcherAssert.assertThat(lessonService.findByName("impossibleName").size(), equalTo(0));
    }

    @Test
    void findLessonByDay() {
        when(lessonDao.findByDay("monday")).thenReturn(Collections.singletonList(lessonList.get(0)));
        when(lessonDao.findByDay("impossibleDay")).thenReturn(new ArrayList<>());

        MatcherAssert.assertThat(lessonService.findLessonByDay("monday").get(0).getDay(), equalTo("monday"));
        MatcherAssert.assertThat(lessonService.findLessonByDay("monday").size(), equalTo(1));
        MatcherAssert.assertThat(lessonService.findLessonByDay("impossibleDay").size(), equalTo(0));
    }

    @Test
    void findLessonByhour() {
        when(lessonDao.findByHour("14")).thenReturn(Collections.singletonList(lessonList.get(1)));
        when(lessonDao.findByHour("impossibleHour")).thenReturn(new ArrayList<>());

        MatcherAssert.assertThat(lessonService.findLessonByhour("14").get(0).getHour(), equalTo("14"));
        MatcherAssert.assertThat(lessonService.findLessonByhour("14").size(), equalTo(1));
        MatcherAssert.assertThat(lessonService.findLessonByhour("impossibleHour").size(), equalTo(0));
    }

    @Test
    void findLessonByCoachId() {
        when(lessonDao.findLessonByCoach_Id(idOne)).thenReturn(Collections.singletonList(lessonList.get(0)));
        when(lessonDao.findLessonByCoach_Id(idTwo)).thenReturn(new ArrayList<>());

        MatcherAssert.assertThat(lessonService.findLessonByCoachId(uuIdOne).get(0).getCoach().getId(), equalTo(idOne));
        MatcherAssert.assertThat(lessonService.findLessonByCoachId(uuIdOne).size(), equalTo(1));
        MatcherAssert.assertThat(lessonService.findLessonByCoachId(uuIdTwo).size(), equalTo(0));
    }

    @Test
    void addLesson() {
        AddNewLesson addNewLesson = new AddNewLesson("Sunday", "10", "MMA", "hard");
        lessonService.addLesson(addNewLesson);
        ArgumentCaptor<Lesson> lessonArgumentCaptor = ArgumentCaptor.forClass(Lesson.class);
        verify(lessonDao).add(lessonArgumentCaptor.capture());

        Lesson lessonSentToDao = lessonArgumentCaptor.getValue();
        MatcherAssert.assertThat(lessonSentToDao.getId(), is(Matchers.notNullValue()));
        MatcherAssert.assertThat(lessonSentToDao.getName(), is("MMA"));
    }

    @Test
    void deleteLesson() {
        Lesson lessonOne =  lessonList.get(0);
        Optional<Lesson> optionalLesson = Optional.of(lessonOne);
        when (lessonDao.findById(idOne)).thenReturn(optionalLesson);
        doNothing().when(lessonDao).delete(optionalLesson.get());

        lessonService.deleteLesson(uuIdOne);
        verify(lessonDao).delete(lessonOne);
    }

    @Test
    void updateLesson() {
        Lesson lessonOne =  lessonList.get(0);
        AddNewLesson updateLesson = new AddNewLesson("monday", "hour", "kevin", "difficulty");

        when(lessonDao.findByHour(updateLesson.getHour())).thenReturn(Collections.singletonList(lessonOne));
        when(lessonDao.findByDay(updateLesson.getDay())).thenReturn(Collections.singletonList(lessonOne));
        doNothing().when(lessonDao).add(lessonOne);

        lessonService.updateLesson(updateLesson);
        verify(lessonDao).add(lessonOne);
    }
}