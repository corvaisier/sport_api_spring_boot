package com.julien.sportapi.exception.CoachException;

import com.julien.sportapi.dto.general.UuId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CoachForbiddenDeleteException extends RuntimeException {

    public CoachForbiddenDeleteException(UuId id) {
        super("This coach: " + id + " can't be changed ! ");
    }
}
