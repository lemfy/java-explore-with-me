package ru.practicum.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.requests.enums.UpdateRequestStatus;
import ru.practicum.requests.model.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> getAllByEventId(int eventId);

    List<Request> getAllByRequesterId(int requesterId);

    List<Request> getAllByEventIdAndRequesterId(int eventId, int requesterId);

    Integer countAllByEventIdAndStatus(int eventId, UpdateRequestStatus status);
}