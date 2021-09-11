package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.SignUpCoach;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.service.CoachService;
import com.julien.sportapi.service.LessonService;
import com.julien.sportapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value ="/administrator",
        produces = { "application/json"}
)
public class AdminController extends CoachController{
    private final PersonService personService;


    public AdminController(CoachService coachService, LessonService lessonService, PersonService personService) {
        super(coachService, lessonService);
        this.personService = personService;
    }

    @PatchMapping("/updatePerson")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody Person person) {
        personService.update(person);
    }

    @DeleteMapping("/deletePerson")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    void delete(@RequestBody UuId id) { personService.delete(id);}

    //how to use super constructor
    @PostMapping("/addCoach")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody SignUpCoach signUpCoach) { coachService.add(signUpCoach);}


}
