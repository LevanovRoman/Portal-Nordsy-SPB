package com.myapp.portalnordsyspb.exceptions;

public class FileExistsException extends RuntimeException{

    public FileExistsException(String message) {
        super(message);
    }
}
