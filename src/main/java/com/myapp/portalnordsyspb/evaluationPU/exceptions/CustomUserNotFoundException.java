package com.myapp.portalnordsyspb.evaluationPU.exceptions;

public class CustomUserNotFoundException extends RuntimeException{
    public CustomUserNotFoundException(String message) {
        super(message);
    }
}
