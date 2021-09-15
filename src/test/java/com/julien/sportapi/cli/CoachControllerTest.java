package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.LessonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CoachController.class)
class CoachControllerTest {

    @MockBean
    private CoachService coachService;
    @MockBean
    private LessonService lessonService;
    @Autowired
    private MockMvc mockMvc;
    UUID idOne = UUID.randomUUID();
    Coach coach = new Coach(idOne, "coachOne", "coachOne", "admin", new ArrayList<>(), new ArrayList<>());


    @Test
    void findLessonByDay() throws Exception {
        when(lessonService.findLessonByDay("monday")).thenReturn(Arrays.asList(
                new Lesson(idOne, "monday", "11", "MMA", "easy", new ArrayList<>(), coach)
        ));
        mockMvc.perform(get("/coach/findLessonByDay")
                        .param("day", "monday")
                )
                .andExpect(jsonPath("$[0].id", is(idOne)))
                .andExpect(jsonPath("$[0].day", is("monday")))
                .andExpect(status().isOk());
    }

    @Test
    void findLessonByhour() {
    }

    @Test
    void findLessonByCoachId() {
    }

    @Test
    void addPersonToCoachList() {
    }

    @Test
    void addLesson() {

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}