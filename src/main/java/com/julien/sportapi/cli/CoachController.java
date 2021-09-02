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

    @GetMapping("/{coachName}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Coach findByName(@PathVariable String coachName) {
        return coachService.findByName(coachName);
    }

    @GetMapping("/{coachId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Coach findById(@PathVariable UUID coachId) {
        return coachService.findById(coachId);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    void add(@RequestParam String coachName) {
        coachService.add(coachName);
    }

     @DeleteMapping("")
     @ResponseStatus(code = HttpStatus.CREATED)
     @ResponseBody void delete(Coach coach) {
        coachService.delete(coach);
    }

    @PatchMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestParam String coachName, String newCoachName) {
        coachService.update(coachName, newCoachName);
    }

}
