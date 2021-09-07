package com.julien.sportapi.exception.GymException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GymByNameNotFoundException extends RuntimeException {

    public GymByNameNotFoundException(String gymName) {
        super("a gym with name " + gymName + " does not exist");
    }
}
