package com.julien.sportapi.exception.PersonException;

import java.util.UUID;

public class UserLoginNotUniqException extends RuntimeException{
    public UserLoginNotUniqException(String error) {
        super(error);
    }
}
