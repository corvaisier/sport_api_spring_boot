package com.julien.sportapi.exception.PersonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonByIdNotFoundException extends RuntimeException{

    public PersonByIdNotFoundException(UUID userId) {
        super("This user id does not exist: " + userId);
    }
}
