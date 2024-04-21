package org.solo.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException(String message) {
        super(message);
    }
}
