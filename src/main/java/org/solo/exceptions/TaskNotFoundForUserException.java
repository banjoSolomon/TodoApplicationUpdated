package org.solo.exceptions;

public class TaskNotFoundForUserException extends RuntimeException {
    public TaskNotFoundForUserException(String message) {
        super(message);
    }
}
