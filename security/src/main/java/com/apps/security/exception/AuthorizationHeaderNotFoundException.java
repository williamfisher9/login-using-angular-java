package com.apps.security.exception;

public class AuthorizationHeaderNotFoundException extends RuntimeException {
    public AuthorizationHeaderNotFoundException(String message){
        super(message);
    }
}