package ru.practicum.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<Error> handleBadRequestException(final BadRequestException badRequestException) {
        log.error("Error code: 400.", badRequestException);
        return new ResponseEntity<>(badRequestException.getApiError(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleDataNotFoundException(final DataNotFoundException dataNotFoundException) {
        log.error("Error code: 404.", dataNotFoundException);
        return new ResponseEntity<>(dataNotFoundException.getApiError(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleConflictException(final ConflictException conflictException) {
        log.error("Error code: 409.", conflictException);
        return new ResponseEntity<>(conflictException.getApiError(), HttpStatus.CONFLICT);
    }
}