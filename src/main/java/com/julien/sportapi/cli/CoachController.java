package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Coach;
import com.julien.sportapi.service.CoachService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coach")
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

    @GetMapping("/name/{coachName}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Coach> findByName(@PathVariable String coachName) {
        return coachService.findByName(coachName);
    }

    @GetMapping("/{coachId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Coach findById(@PathVariable UUID coachId) {
        return coachService.findById(coachId);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody String coachName) { coachService.add(coachName);}

     @DeleteMapping("")
     @ResponseStatus(code = HttpStatus.CREATED)
     @ResponseBody
     void delete(@RequestBody UUID coachId) {
        coachService.delete(coachId);
    }

    @PostMapping("/attach")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addCoach(@RequestBody UUID personId, @RequestBody UUID coachId) {
        coachService.attachPerson(personId, coachId);
    }

    @PatchMapping("/admin")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestBody Coach coach) {
        coachService.update(coach);
    }

}
