package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.person.PersonDto;
import com.julien.sportapi.dto.person.PersonDtoForUpdate;
import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.LessonService;
import com.julien.sportapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value ="/person",
        produces = { "application/json"}
)
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final LessonService lessonService;
    private final CoachService coachService;


    @GetMapping("/lessons")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Lesson> findAllLessons() { return lessonService.findAll();}

    @GetMapping("/coaches")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Coach> findAllCoaches() { return coachService.findAll();}

    @PatchMapping("/update")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody PersonDtoForUpdate personDtoForUpdate) {
        personService.update(personDtoForUpdate);
    }

    @DeleteMapping("")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    void delete(@RequestBody UuId id) { personService.delete(id);}

}

