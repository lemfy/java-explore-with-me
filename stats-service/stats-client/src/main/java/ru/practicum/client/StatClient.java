package ru.practicum.client;

import org.springframework.http.ResponseEntity;
import ru.practicum.StatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatClient {

    ResponseEntity<List<StatsDto>> getStats(LocalDateTime start, LocalDateTime end, List<String> uris);

    ResponseEntity<String> saveHit(String app, String uri, String ipAddress, LocalDateTime timestamp);
}