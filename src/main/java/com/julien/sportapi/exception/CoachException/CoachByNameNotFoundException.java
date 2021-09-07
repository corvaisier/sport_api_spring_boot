package com.julien.sportapi.exception.CoachException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoachByNameNotFoundException extends RuntimeException {

    public CoachByNameNotFoundException(String coachName) {
        super("a coach with name " + coachName + " does not exist");
    }
}