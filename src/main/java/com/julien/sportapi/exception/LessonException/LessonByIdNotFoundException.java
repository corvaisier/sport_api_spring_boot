package com.julien.sportapi.exception.LessonException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LessonByIdNotFoundException extends RuntimeException{
    public LessonByIdNotFoundException(UUID id){
        super("This lesson id does not exist: " + id);}
}
