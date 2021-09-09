package com.julien.sportapi.cli;

import com.julien.sportapi.domain.Person;
import com.julien.sportapi.dto.SignUpPerson;
import com.julien.sportapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value ="/person",
        produces = { "application/json"}
)
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<Person> findAll() { return personService.findAll();}

    @PostMapping("/sign-up")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void add(@RequestBody SignUpPerson signUpPerson) { personService.add(signUpPerson);}

//    @Secured("admin")
//    @DeleteMapping("/{userId}")
//    @ResponseStatus(code = HttpStatus.CREATED)
//    void delete(@PathVariable UUID id) { personService.delete(id);}

}

