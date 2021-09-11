package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.AttachCoachPerson;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.service.CoachService;

import com.julien.sportapi.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value ="/coach",
        produces = { "application/json"}
)
@AllArgsConstructor
public class CoachController {
    private final CoachService coachService;
    private final LessonService lessonService;


//    @GetMapping("/name/{name}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @ResponseBody
//    List<Coach> findByName(@PathVariable String name) {
//        return coachService.findByName(name);
//    }

//    @GetMapping("/findCoachById")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @ResponseBody
//    Coach findById(@RequestBody UuId id) {
//        return coachService.findById(id);
//    }

    @GetMapping("/findLessonByDay")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findPerson(@RequestBody String day) {
        return coachService.findLessonByDay(day);
    }

    @GetMapping("/findLessonByHour")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findPerson(@RequestBody String hour) {
        return coachService.findLessonByhour(hour);
    }

    @GetMapping("/findPersonnalLessonByHour")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findPerson(@RequestBody UuId id) {
        return coachService.findLessonByCoachId(id);
    }

    @PostMapping("/addPersonToCoachList")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addPersonToCoachList(@RequestBody AddPersonToCoachList addPersonToCoachList) {
        coachService.attachPerson(addPersonToCoachList);
    }

    @PostMapping("/addLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addLesson(@RequestBody AddLesson addLesson) {
        lessonService.add(addLesson);
    }

    @PatchMapping("/updateLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody Lesson lesson) {
        lessonService.update(lesson);
    }

    @DeleteMapping("/deleteLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void delete(@RequestBody UuId id) {
        lessonService.delete(id);
    }

    @PatchMapping("/update")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody Coach coach) {
        coachService.update(coach);
    }
}
