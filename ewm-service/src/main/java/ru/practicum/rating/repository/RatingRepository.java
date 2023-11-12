package ru.practicum.rating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.rating.enums.RatingType;
import ru.practicum.rating.model.Rating;
import ru.practicum.rating.model.RatingCount;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByEventIdAndType(int eventId, RatingType type);

    Optional<Rating> findByEventIdAndUserId(int eventId, int userId);

    @Query(value = "SELECT COUNT(e.id) totalCount, COUNT(CASE WHEN e.like_type = 'LIKE' THEN 1 END) likesCount " +
            "FROM event_likes e " + "WHERE e.event_id = :id", nativeQuery = true)
    RatingCount getRating(@Param("id") int id);
}