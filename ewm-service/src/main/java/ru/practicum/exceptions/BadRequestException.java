package ru.practicum.exceptions;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message, "Incorrectly made request.", "BAD_REQUEST");
    }
}