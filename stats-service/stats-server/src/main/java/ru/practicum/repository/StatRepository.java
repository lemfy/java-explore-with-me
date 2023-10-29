package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.StatsDto;
import ru.practicum.model.Endpoint;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Endpoint, Integer> {
    @Query("SELECT NEW ru.practicum.StatsDto(h.app, h.uri, COUNT(h.ipAddress)) " +
            "FROM Endpoint h " +
            "WHERE (h.timestamp BETWEEN :start AND :end) " +
            "GROUP BY h.uri, h.app " +
            "ORDER BY COUNT(h.ipAddress) DESC")
    List<StatsDto> getStatsBetweenStartAndEndGroupByUri(
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("SELECT NEW ru.practicum.StatsDto(h.app, h.uri, COUNT(DISTINCT(h.ipAddress))) " +
            "FROM Endpoint h " +
            "WHERE (h.timestamp BETWEEN :start AND :end) " +
            "GROUP BY h.uri, h.app " +
            "ORDER BY COUNT(DISTINCT(h.ipAddress)) DESC")
    List<StatsDto> getUniqueStatsBetweenStartAndEndGroupByUri(
            LocalDateTime start,
            LocalDateTime end
    );

    @Query("SELECT NEW ru.practicum.StatsDto(h.app, h.uri, COUNT(h.ipAddress)) " +
            "FROM Endpoint h " +
            "WHERE h.uri IN :uris " +
            "AND (h.timestamp BETWEEN :start AND :end) " +
            "GROUP BY h.uri, h.app " +
            "ORDER BY COUNT(h.ipAddress) DESC")
    List<StatsDto> getStatsByUrisAndBetweenStartAndEndGroupByUri(
            LocalDateTime start,
            LocalDateTime end,
            Collection<String> uris
    );

    @Query("SELECT NEW ru.practicum.StatsDto(h.app, h.uri, COUNT(DISTINCT(h.ipAddress))) " +
            "FROM Endpoint h " +
            "WHERE h.uri IN :uris " +
            "AND (h.timestamp BETWEEN :start AND :end) " +
            "GROUP BY h.uri, h.app " +
            "ORDER BY COUNT(DISTINCT(h.ipAddress)) DESC")
    List<StatsDto> getUniqueStatsByUrisAndBetweenStartAndEndGroupByUri(
            LocalDateTime start,
            LocalDateTime end,
            Collection<String> uris
    );
}