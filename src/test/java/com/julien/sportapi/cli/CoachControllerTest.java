package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.service.CoachService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(CoachController.class)
class CoachControllerTest {

    @MockBean
    private CoachService coachService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception{
        when(coachService.findAll()).thenReturn(Collections.singletonList(
                new Coach(UUID.fromString("11111111-1111-1111-1111-111111111111"), "superCoach")
        ));

        mockMvc.perform(get("/coach"))
                .andExpect(jsonPath("$[0].coachID", is("11111111-1111-1111-1111-111111111111")))
                .andExpect(jsonPath("$[0].coachName", is("superCoach")));
    }

    @Test
    void findByName() {

    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {

    }
}