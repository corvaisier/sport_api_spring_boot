package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.LessonService;
import com.julien.sportapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoachController.class)
class CoachControllerTest {

    @MockBean
    private LessonService lessonService;
    @MockBean
    private PersonService personService;
    @MockBean
    private CoachService coachService;
    @Autowired
    private MockMvc mockMvc;
    UUID idOne = UUID.randomUUID();
    Coach coach = new Coach(idOne, "coachOne", "coachOne@gmail.com", "coach", "coach", new ArrayList<>(), new ArrayList<>());


    @Test
    void authentication() throws Exception {
        when(lessonService.findLessonByDay("monday")).thenReturn(Collections.singletonList(
                new Lesson(idOne, "monday", "11", "MMA", "easy", new ArrayList<>(), coach)
        ));
        mockMvc.perform(get("/coach/findLessonByDay")
                        .param("day", "monday")
                )
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    //TODO : be able to tests with authentication all functionalities

//    @Test
//    void findLessonByDay() throws Exception {
//        when(lessonService.findLessonByDay("monday")).thenReturn(Collections.singletonList(
//                new Lesson(idOne, "monday", "11", "MMA", "easy", new ArrayList<>(), coach)
//        ));
//        mockMvc.perform(get("/coach/findLessonByDay")
//                        .param("day", "monday")
//                )
//                .andExpect(jsonPath("$[0].id", is(idOne)))
//                .andExpect(jsonPath("$[0].day", is("monday")))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void findLessonByhour() {
//    }
//
//    @Test
//    void findLessonByCoachId() {
//    }
//
//    @Test
//    void addPersonToCoachList() {
//    }
//
//    @Test
//    void addLesson() {
//
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
}