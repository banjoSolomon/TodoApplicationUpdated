package org.solo.exceptions;

public class UsernameCannotBeNullException extends RuntimeException {
    public UsernameCannotBeNullException(String message) {
        super(message);
    }
}
