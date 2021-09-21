package com.julien.sportapi.exception.CoachException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CoachEmailNotUniqException extends RuntimeException {

    public CoachEmailNotUniqException(String coachEmail) {
        super("a coach with email " + coachEmail + " is not uniq");
    }
}
