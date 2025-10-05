package com.example.demo.exception;

public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException(String message ) {
        super( message );
    }
}
