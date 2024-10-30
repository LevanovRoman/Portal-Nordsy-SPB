package com.myapp.portalnordsyspb.exceptions;

public class MaxvalueNotFoundException extends RuntimeException{
    public MaxvalueNotFoundException(String message) {
        super(message);
    }
}
