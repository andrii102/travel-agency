package com.epam.finaltask.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    private final String errorCode;
    public EntityAlreadyExistsException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
