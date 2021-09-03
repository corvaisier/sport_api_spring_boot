package com.julien.sportapi.exception.GymException;

import java.util.UUID;

public class GymByIdNotFoundException extends RuntimeException {

    public GymByIdNotFoundException(UUID gymId) {
        super("a gym with id " + gymId + " does not exist");
    }
}
