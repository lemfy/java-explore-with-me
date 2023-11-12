package ru.practicum.rating.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.stat.interval.IntervalUtils;
import org.springframework.stereotype.Service;
import ru.practicum.events.model.Event;
import ru.practicum.events.repository.EventRepository;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.DataNotFoundException;
import ru.practicum.rating.enums.RatingType;
import ru.practicum.rating.model.Rating;
import ru.practicum.rating.model.RatingCount;
import ru.practicum.rating.repository.RatingRepository;
import ru.practicum.users.dto.UserDto;
import ru.practicum.users.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final EventRepository eventRepository;
    private final UserService userService;

    private void recountEventRating(int eventId) {
        RatingCount ratingCount = ratingRepository.getRating(eventId);
        int totalCnt = ratingCount.getTotalCount();
        int likesCnt = ratingCount.getLikesCount();
        double rating = 0F;
        if (likesCnt > 0 && totalCnt > 0) {
            rating = IntervalUtils
                    .getWilsonScoreInterval(totalCnt, likesCnt, 0.96)
                    .getLowerBound();
        }

        Event event = findEventById(eventId);
        event.setRating(rating);
        eventRepository.save(event);
    }

    private Event findEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new DataNotFoundException(Event.class.getName(), eventId)
                );
    }

    private Rating findLikeByUser(int eventId, int userId) {
        return ratingRepository.findByEventIdAndUserId(eventId, userId)
                .orElseThrow(() -> new DataNotFoundException(Rating.class.getName(), eventId)
                );
    }

    @Transactional
    public void addLike(int eventId, int userId, RatingType type) {
        userService.checkUserById(userId);
        Event event = findEventById(eventId);
        if (event.getInitiator().getId() == userId) {
            throw new ConflictException(String.format("User can not post %s for his event", type));
        }
        Optional<Rating> rating = ratingRepository.findByEventIdAndUserId(eventId, userId);
        rating.ifPresent(
                like -> {
                    if (like.getType().equals(type)) {
                        throw new ConflictException(String.format("User can not post event %s twice", type));
                    } else {
                        ratingRepository.delete(like);
                    }
                }
        );

        Rating like = Rating.builder()
                .userId(userId)
                .eventId(eventId)
                .createdOn(LocalDateTime.now())
                .type(type)
                .build();
        ratingRepository.save(like);
        recountEventRating(eventId);
    }

    @Transactional
    public void removeLike(int eventId, int userId) {
        userService.checkUserById(userId);
        findEventById(eventId);
        Rating like = findLikeByUser(eventId, userId);
        ratingRepository.delete(like);
        recountEventRating(eventId);
    }

    public List<UserDto> getRatingUsers(int eventId, RatingType type, int from, int size) {
        List<Integer> userIds = ratingRepository.findByEventIdAndType(eventId, type)
                .stream()
                .map(Rating::getId)
                .collect(Collectors.toList());

        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }
        return userService.findAllByUser(userIds, from, size);
    }
}