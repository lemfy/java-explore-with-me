package ru.practicum.exceptions;

public class DataNotFoundException extends ApiException {
    public DataNotFoundException(String objectClass, int id) {
        super(String.format("%s with id=%d was not found", objectClass, id), "The required object was not found.", "NOT_FOUND");
    }
}