package ru.practicum.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.utils.DateTimeService;
import ru.practicum.events.enums.AdminStateAction;
import ru.practicum.events.model.Location;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class UpdateEventAdminRequest {
    @Size(min = 3, max = 120)
    private String title;
    @Size(min = 20, max = 7000)
    private String description;
    @Size(min = 20, max = 2000)
    private String annotation;
    private Integer category;
    @FutureOrPresent
    @JsonFormat(pattern = DateTimeService.DATE_TIME_FORMAT)
    private LocalDateTime eventDate;
    private AdminStateAction stateAction;
    private Location location;
    private Boolean paid;
    private Boolean requestModeration;
    private Integer participantLimit;
}