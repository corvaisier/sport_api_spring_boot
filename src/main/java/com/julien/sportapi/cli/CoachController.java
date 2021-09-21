package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.coach.AddPersonToCoachList;
import com.julien.sportapi.dto.coach.CoachDtoForUpdate;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.lesson.LessonDto;
import com.julien.sportapi.dto.lesson.LessonDtoForUpdate;
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
    List<Lesson> findLessonByDay(@RequestBody String day) {
        return lessonService.findLessonByDay(day);
    }

    @GetMapping("/findLessonByhour")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Lesson> findLessonByhour(@RequestBody String hour) {
        return lessonService.findLessonByhour(hour);
    }

    @GetMapping("/findLessonByCoachId")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Lesson> findLessonByCoachId(@RequestBody UuId id) {
        return lessonService.findLessonByCoachId(id);
    }

    @PostMapping("/addPersonToCoachList")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addPersonToCoachList(@RequestBody AddPersonToCoachList addPersonToCoachList) {
        coachService.attachPerson(addPersonToCoachList);
    }
    @PostMapping("/addLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addLesson(@RequestBody LessonDto lessonDto) {
        lessonService.addLesson(lessonDto);
    }

    @PatchMapping("/updateLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody LessonDtoForUpdate lesson) {
        lessonService.updateLesson(lesson);
    }

    @DeleteMapping("/deleteLesson")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void delete(@RequestBody UuId id) {
        lessonService.deleteLesson(id);
    }

    @PatchMapping("/update")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody CoachDtoForUpdate coachDtoForUpdate) {
        coachService.update(coachDtoForUpdate);
    }
}
