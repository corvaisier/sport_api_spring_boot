package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Gym;
import com.julien.sportapi.service.GymService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gym")
public class GymController {
    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    List<Gym> findAll() {
        return gymService.findAll();
    }

    @GetMapping("/{gymName}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Optional<Gym> findByName(@PathVariable String gymName) {
        return gymService.findByName(gymName);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    void add(@RequestParam String gymName, String gymLocation) {
        gymService.add(gymName, gymLocation);
    }

    @DeleteMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody void delete(String gymName) {
        gymService.delete(gymName);
    }

    @PatchMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    void update(@RequestParam String gymName, String newGymName) {
        gymService.update(gymName, newGymName);
    }
}
