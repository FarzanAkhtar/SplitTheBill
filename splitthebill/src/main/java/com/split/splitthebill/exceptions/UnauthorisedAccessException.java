package com.split.splitthebill.exceptions;

public class UnauthorisedAccessException extends RuntimeException {
    public UnauthorisedAccessException() {
        super();
    }

    public UnauthorisedAccessException(String message) {
        super(message);
    }

    public UnauthorisedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorisedAccessException(Throwable cause) {
        super(cause);
    }
}
