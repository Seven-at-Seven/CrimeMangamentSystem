package com.sevenatseven.exceptions;

public class DoesNotExistException extends RuntimeException {
    public DoesNotExistException(String message) {
        super(message);
    }
}
