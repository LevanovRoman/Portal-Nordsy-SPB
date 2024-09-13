package com.myapp.portalnordsyspb.exceptions;

public class CustomUserNotFoundException extends RuntimeException{
    public CustomUserNotFoundException(String message) {
        super(message);
    }
}
