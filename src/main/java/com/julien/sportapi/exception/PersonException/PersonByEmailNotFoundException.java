package com.julien.sportapi.exception.PersonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonByEmailNotFoundException extends RuntimeException{

    public PersonByEmailNotFoundException(String email) {
        super("This user id does not exist: " + email);
    }
}
