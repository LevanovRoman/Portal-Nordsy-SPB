package com.myapp.portalnordsyspb.exceptions;

public class SectionNotFoundException extends RuntimeException{
    public SectionNotFoundException(String message) {
        super(message);
    }
}
