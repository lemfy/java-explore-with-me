package ru.practicum.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiException extends RuntimeException {
    private final String reason;
    private final String status;

    public ApiException(String message, String reason, String status) {
        super(message);
        this.reason = reason;
        this.status = status;
    }

    public Error getApiError() {
        return Error.builder()
                .message(this.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}