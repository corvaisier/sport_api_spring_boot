package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.SignUp;
import com.julien.sportapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService userService) {
        this.personService = userService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findAll() { return personService.findAll();}

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    Person findById(@PathVariable UUID userId) { return personService.findById(userId);}

    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody SignUp signUp) { personService.add(signUp);}

    @Secured("admin")
    @DeleteMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    void delete(@PathVariable UUID userId) { personService.delete(userId);}

}

