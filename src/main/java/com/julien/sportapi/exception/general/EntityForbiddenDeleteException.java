package com.julien.sportapi.exception.general;

import com.julien.sportapi.dto.general.UuId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class EntityForbiddenDeleteException extends RuntimeException {

    public EntityForbiddenDeleteException(UuId id) {
        super("This entity: " + id + " can't be changed ! ");
    }
}
