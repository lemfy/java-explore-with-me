package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointDto;
import ru.practicum.StatsDto;
import ru.practicum.mapper.EndpointMapper;
import ru.practicum.model.Endpoint;
import ru.practicum.repository.StatRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatService {
    private final StatRepository statRepository;
    private final EndpointMapper endpointHitMapper;

    public ResponseEntity<String> saveHit(EndpointDto endpointDto) {
        Endpoint endpointHit = endpointHitMapper.fromDto(endpointDto);
        statRepository.save(endpointHit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<StatsDto>> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean isUnique) {
        List<StatsDto> stats;
        if (uris != null && !uris.isEmpty()) {
            if (isUnique) {
                stats = statRepository.getUniqueStatsByUrisAndBetweenStartAndEndGroupByUri(start, end, uris);
            } else {
                stats = statRepository.getStatsByUrisAndBetweenStartAndEndGroupByUri(start, end, uris);
            }
        } else {
            if (isUnique) {
                stats = statRepository.getUniqueStatsBetweenStartAndEndGroupByUri(start, end);
            } else {
                stats = statRepository.getStatsBetweenStartAndEndGroupByUri(start, end);
            }
        }
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}