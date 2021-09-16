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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration
@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @MockBean
    private LessonService lessonService;
    @MockBean
    private PersonService personService;
    @MockBean
    private CoachService coachService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    UUID idOne = UUID.randomUUID();
    Coach coach = new Coach(idOne, "coachOne", "coachOne", "admin", new ArrayList<>(), new ArrayList<>());

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
//    @WithMockUser
//    void findAllLessons() throws Exception {
//        when(lessonService.findAll()).thenReturn(Collections.singletonList(
//                new Lesson(idOne, "monday", "11", "MMA", "easy", new ArrayList<>(), coach)
//        ));
//        mockMvc.perform(get("/person/lessons")
//                )
//                .andExpect(status().isFound());
//    }
//
//    @Test
//    void findAllCoaches() {
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