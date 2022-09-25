package com.flight.qrpinger.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Could not find such a user");
    }

}
