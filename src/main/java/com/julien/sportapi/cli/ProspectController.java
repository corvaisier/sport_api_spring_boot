package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.person.SignUpPerson;
import com.julien.sportapi.service.LessonService;
import com.julien.sportapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value ="/prospect",
        produces = { "application/json"}
)
@AllArgsConstructor
public class ProspectController {
    private final PersonService personService;
    private final LessonService lessonService;

    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody SignUpPerson signUpPerson) { personService.add(signUpPerson);}

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Lesson> findAll() { return lessonService.findAll();}
}
