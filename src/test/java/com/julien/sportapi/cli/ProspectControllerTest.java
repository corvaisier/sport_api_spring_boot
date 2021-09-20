package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.person.PersonDto;
import com.julien.sportapi.service.LessonService;
import com.julien.sportapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProspectController.class)
class ProspectControllerTest {
    @MockBean
    private LessonService lessonService;
    @MockBean
    private PersonService personService;
    @Autowired
    private MockMvc mockMvc;

    UUID idOne = UUID.randomUUID();
    Coach coach = new Coach(idOne, "coachOne", "coachOne", "admin", new ArrayList<>(), new ArrayList<>());

    @Test
    void add() throws Exception {
        String bodyAsJson = "{\n" +
                "  \"name\": \"name\",\n" +
                "  \"firstName\": \"firstName\",\n" +
                "  \"email\": \"email@email.com\",\n" +
                "  \"password\": \"password\"\n" +
                "}\n";
        PersonDto signUpPerson = new PersonDto("name", "firstName", "email@email.com", "password");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/prospect/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyAsJson)
                )
                .andExpect(status().isCreated());
        verify(personService).add(signUpPerson);
    }

    @Test
    void findAll() throws Exception {
        when(lessonService.findAll()).thenReturn(Collections.singletonList(
                new Lesson(idOne, "monday", "11", "MMA", "easy", new ArrayList<>(), coach)
        ));
        mockMvc.perform(get("/prospect")
                        .param("day", "monday")
                )
                .andExpect(jsonPath("$[0].day", is("monday")))
                .andExpect(status().isCreated());
    }
}