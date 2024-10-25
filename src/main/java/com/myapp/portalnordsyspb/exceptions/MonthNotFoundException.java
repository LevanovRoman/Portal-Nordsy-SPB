package com.myapp.portalnordsyspb.exceptions;

public class MonthNotFoundException extends RuntimeException{
    public MonthNotFoundException(String message) {
        super(message);
    }
}
