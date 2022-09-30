package com.flight.qrpinger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyAUserException extends RuntimeException {

    public AlreadyAUserException() {
        super();
    }

    public AlreadyAUserException(String message) {
        super(message);
    }

    public AlreadyAUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
