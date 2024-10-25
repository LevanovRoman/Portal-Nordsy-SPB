package com.myapp.portalnordsyspb.exceptions;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> globalExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AreaNotFoundException.class)
    public MessageDto areaNotFound(AreaNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WeekNotFoundException.class)
    public MessageDto weekNotFound(WeekNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CriterionNotFoundException.class)
    public MessageDto criterionNotFound(CriterionNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmailAlreadyExistException.class)
    public MessageDto emailAlreadyExist(EmailAlreadyExistException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomUserNotFoundException.class)
    public MessageDto usernameNotFound(CustomUserNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RefreshTokenExpiredException.class)
    public MessageDto refreshTokenExpired(RefreshTokenExpiredException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public MessageDto refreshTokenNotFound(RefreshTokenNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChapterNotFoundException.class)
    public MessageDto chapterNotFound(ChapterNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFoundException.class)
    public MessageDto postNotFound(PostNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MonthNotFoundException.class)
    public MessageDto monthNotFound(MonthNotFoundException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyFileException.class)
    public MessageDto emptyFileException(EmptyFileException ex){
        return new MessageDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileExistsException.class)
    public MessageDto fileExistsException(FileExistsException ex){
        return new MessageDto(ex.getMessage());
    }


}
