package org.example.healthcare;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException() {
        super();
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
