package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.events.model.Location;
import ru.practicum.utils.DateBefore;
import ru.practicum.utils.DateTimeService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class NewEventDto {
    @Size(min = 3, max = 100)
    private String title;
    @NotBlank
    @Size(min = 20, max = 1000)
    private String annotation;
    @NotBlank
    @Size(min = 20, max = 5000)
    private String description;
    @Positive
    private int category;
    @DateBefore(hours = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeService.DATE_TIME_FORMAT)
    private LocalDateTime eventDate;
    @NotNull
    private Location location;
    private Boolean paid;
    private Boolean requestModeration;
    private int participantLimit;
}