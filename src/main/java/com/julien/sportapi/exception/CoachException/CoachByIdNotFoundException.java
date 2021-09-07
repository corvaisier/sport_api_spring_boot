package com.julien.sportapi.exception.CoachException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoachByIdNotFoundException extends RuntimeException {

    public CoachByIdNotFoundException(UUID coachId) {
        super("a coach with id " + coachId + " does not exist");
    }
}
