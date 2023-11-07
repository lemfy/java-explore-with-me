package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.events.enums.UserStateAction;
import ru.practicum.events.model.Location;
import ru.practicum.utils.DateBefore;
import ru.practicum.utils.DateTimeService;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class UpdateEventUserRequest {
    @Size(min = 3, max = 100)
    private String title;
    @Size(min = 20, max = 5000)
    private String description;
    @Size(min = 20, max = 1000)
    private String annotation;
    private Integer category;
    @DateBefore(hours = 2)
    @JsonFormat(pattern = DateTimeService.DATE_TIME_FORMAT)
    private LocalDateTime eventDate;
    private UserStateAction stateAction;
    private Location location;
    private Boolean paid;
    private Boolean requestModeration;
    private Integer participantLimit;
}