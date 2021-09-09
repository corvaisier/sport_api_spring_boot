package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.coach.AttachCoachPerson;
import com.julien.sportapi.dto.coach.SignUpCoach;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.service.CoachService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value ="/coach",
        produces = { "application/json"}
)
public class CoachController {
    private final CoachService coachService;

    public CoachController(CoachService CoachService) {
        this.coachService = CoachService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    List<Coach> findAll() {
        return coachService.findAll();
    }

//    @GetMapping("/name/{name}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @ResponseBody
//    List<Coach> findByName(@PathVariable String name) {
//        return coachService.findByName(name);
//    }

    @GetMapping("/findCoachById")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Coach findById(@RequestBody UuId id) {
        return coachService.findById(id);
    }

    @GetMapping("/findPerson")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findPerson(@RequestBody UuId id) {
        return coachService.findPerson(id);
    }

//    @PostMapping("/sign-up")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @ResponseBody
//    void add(@RequestBody SignUpCoach signUpCoach) { coachService.add(signUpCoach);}

    @DeleteMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void delete(@RequestBody UUID coachId) {
        coachService.delete(coachId);
    }

    @PostMapping("/attachPerson")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCoach(@RequestBody AttachCoachPerson attachCoachPerson) {
        coachService.attachPerson(attachCoachPerson);
    }

//    @PatchMapping("/admin")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    void update(@RequestBody Coach coach) {
//        coachService.update(coach);
//    }
}
