package com.julien.sportapi.exception.CoachException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CoachNameNotUniqException extends RuntimeException {

    public CoachNameNotUniqException(String coachName) {
        super("a coach with name " + coachName + " is not uniq");
    }
}
