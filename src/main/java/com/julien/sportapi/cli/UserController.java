package com.julien.sportapi.cli;

import com.julien.sportapi.domain.User;
import com.julien.sportapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    List<User> findAll() { return userService.findAll();}

    @GetMapping("/{userId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    User findById(@PathVariable UUID userId) { return userService.findById(userId);}

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    void add(@RequestParam String userName, String userLogin, String userPassword, String userStatus) { userService.add(userName, userLogin, userPassword, userStatus);}

    @DeleteMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    void delete(UUID userId) { userService.delete(userId);}

}

