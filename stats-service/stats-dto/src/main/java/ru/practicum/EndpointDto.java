package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointDto {
    @NotNull(message = "Parameter 'app' is required")
    private String app;

    @NotNull(message = "Parameter 'uri' is required")
    private String uri;

    @NotNull(message = "Parameter 'ipAddress' is required")
    @Pattern(message = "Parameter 'ipAddress' is invalidated",
            regexp = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String ipAddress;

    @NotNull(message = "Parameter 'timestamp' is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}