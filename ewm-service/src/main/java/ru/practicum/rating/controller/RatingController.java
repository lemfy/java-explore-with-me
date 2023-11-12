package ru.practicum.rating.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.rating.enums.RatingType;
import ru.practicum.rating.service.RatingService;
import ru.practicum.users.dto.UserDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/likes/{eventId}")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping
    public List<UserDto> getLikes(@PathVariable int eventId,
                                  @Valid @RequestParam RatingType type,
                                  @RequestParam(defaultValue = "0") int from,
                                  @RequestParam(defaultValue = "10") int size) {
        return ratingService.getRatingUsers(eventId, type, from, size);
    }
}