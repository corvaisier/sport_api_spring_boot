package com.julien.sportapi.exception.CoachException;

import java.util.UUID;

public class CoachIdNotFoundException extends RuntimeException {

    public CoachIdNotFoundException(UUID coachId) {
        super("a coach with id " + coachId + " does not exist");
    }
}
