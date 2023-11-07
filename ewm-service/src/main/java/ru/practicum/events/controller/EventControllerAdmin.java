package ru.practicum.events.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.UpdateEventAdminRequest;
import ru.practicum.events.enums.EventState;
import ru.practicum.events.service.EventService;
import ru.practicum.utils.DateTimeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class EventControllerAdmin {
    private final EventService eventService;

    @PatchMapping("/{eventId}")
    public EventFullDto setStatus(@PathVariable int eventId, @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return eventService.updateByAdmin(eventId, updateEventAdminRequest);
    }

    @GetMapping
    public List<EventFullDto> search(@RequestParam(required = false) List<Integer> users,
                                     @RequestParam(required = false) List<EventState> states,
                                     @RequestParam(required = false) List<Integer> categories,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = DateTimeService.DATE_TIME_FORMAT) LocalDateTime rangeStart,
                                     @RequestParam(required = false) @DateTimeFormat(pattern = DateTimeService.DATE_TIME_FORMAT) LocalDateTime rangeEnd,
                                     @RequestParam(defaultValue = "0") int from,
                                     @RequestParam(defaultValue = "10") int size) {
        return eventService.findByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}