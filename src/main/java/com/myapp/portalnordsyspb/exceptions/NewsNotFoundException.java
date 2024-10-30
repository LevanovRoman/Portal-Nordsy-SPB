package com.myapp.portalnordsyspb.exceptions;

public class NewsNotFoundException extends RuntimeException{
    public NewsNotFoundException(String message) {
        super(message);
    }
}
