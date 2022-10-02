package com.flight.qrpinger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class FailedToEmailException extends MessagingException {

    public FailedToEmailException() {
        super();
    }

    public FailedToEmailException(String message) {
        super(message);
    }

    public FailedToEmailException(String message, Exception exception) {
        super(message, exception);
    }

}
