package com.postit.postit.common.exceptions;

public class DuplicatePostException extends RuntimeException {
    public DuplicatePostException(String message) {
        super(message);
    }
}
