package ru.practicum.exceptions;

public class ConflictException extends ApiException {
    public ConflictException(String message) {
        super(message, "Integrity constraint has been violated.", "CONFLICT");
    }
}