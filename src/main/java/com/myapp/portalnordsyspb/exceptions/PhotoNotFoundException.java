package com.myapp.portalnordsyspb.exceptions;

public class PhotoNotFoundException extends RuntimeException{
    public PhotoNotFoundException(String message) {
        super(message);
    }
}
