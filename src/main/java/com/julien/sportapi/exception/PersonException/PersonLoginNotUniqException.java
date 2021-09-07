package com.julien.sportapi.exception.PersonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PersonLoginNotUniqException extends RuntimeException{

    public PersonLoginNotUniqException(String login) {
        super("This login is not uniq :" + login);
    }
}
