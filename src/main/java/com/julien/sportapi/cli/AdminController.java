package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.dto.coach.CoachDto;
import com.julien.sportapi.dto.coach.CoachDtoForUpdate;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.lesson.LessonDto;
import com.julien.sportapi.dto.person.PersonDtoForUpdate;
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
@AllArgsConstructor
public class AdminController {
    private final PersonService personService;
    private final CoachService coachService;
    private final LessonService lessonService;

    @PatchMapping("/updatePerson")
    @ResponseStatus(code = HttpStatus.CREATED)
    void updatePerson(@RequestBody PersonDtoForUpdate personDtoForUpdate) {
        personService.update(personDtoForUpdate);
    }

    @DeleteMapping("/deletePerson")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    void deletePerson(@RequestBody UuId id) { personService.delete(id);}

    //how to use super constructor
    @PostMapping("/addCoach")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody CoachDto signUpCoach) { coachService.add(signUpCoach);}

    @PatchMapping("/updateCoach")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody CoachDtoForUpdate coachDtoForUpdate) {
        coachService.update(coachDtoForUpdate);
    }

    @DeleteMapping("/deleteCoach")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    void delete(@RequestBody UuId id) { coachService.delete(id);}

    @PostMapping("/addLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addLesson(@RequestBody LessonDto lessonDto) {
        lessonService.addLesson(lessonDto);
    }

    @PatchMapping("/updateLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    void updateLesson(@RequestBody LessonDto lesson) {
        lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void deleteLesson(@RequestBody UuId id) {
        lessonService.deleteLesson(id);
    }

}
