package com.amigoscode.person.exception;

public class ResourceNotFoundExecption extends RuntimeException {
    public ResourceNotFoundExecption(String message) {
        super(message);
    }
    public ResourceNotFoundExecption(String message, Throwable cause) {
        super(message, cause);
    }
}
