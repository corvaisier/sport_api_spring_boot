package com.julien.sportapi.exception.CoachException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CoachPersonAlreadyExistException extends RuntimeException {

    public CoachPersonAlreadyExistException(UUID id) {
        super("This person: " + id + " already exist ! ");
    }
}