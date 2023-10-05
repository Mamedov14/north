package ru.java.north.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.java.north.exception.NotFoundException;

@Slf4j
@RestControllerAdvice
public class NoteExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex, WebRequest request) {
        log.error("Not found exception. Message: {}. Request: {}", ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
