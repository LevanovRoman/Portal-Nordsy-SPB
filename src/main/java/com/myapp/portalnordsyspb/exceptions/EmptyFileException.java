package com.myapp.portalnordsyspb.exceptions;

public class EmptyFileException extends RuntimeException{
    public EmptyFileException(String message) {
        super(message);
    }
}
