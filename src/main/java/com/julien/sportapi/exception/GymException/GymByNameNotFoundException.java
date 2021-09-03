package com.julien.sportapi.exception.GymException;

public class GymByNameNotFoundException extends RuntimeException {

    public GymByNameNotFoundException(String gymName) {
        super("a gym with name " + gymName + " does not exist");
    }
}
