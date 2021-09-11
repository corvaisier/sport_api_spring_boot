package com.julien.sportapi.exception.LessonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class LessonDateTimeNotValideException extends RuntimeException{
    public LessonDateTimeNotValideException(String hour, String day){super("\n" +
            "this time slot is already taken: " + hour + day);}
}
