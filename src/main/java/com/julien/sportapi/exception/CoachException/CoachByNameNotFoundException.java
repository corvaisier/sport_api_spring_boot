package com.julien.sportapi.exception.CoachException;

public class CoachByNameNotFoundException extends RuntimeException {

    public CoachByNameNotFoundException(String coachName) {
        super("a coach with name " + coachName + " does not exist");
    }
}