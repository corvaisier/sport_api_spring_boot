package com.julien.sportapi.exception.GymException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GymByIdNotFoundException extends RuntimeException {

    public GymByIdNotFoundException(UUID gymId) {
        super("a gym with id " + gymId + " does not exist");
    }
}
