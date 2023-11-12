package ru.practicum.rating.model;

import lombok.*;
import ru.practicum.rating.enums.RatingType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "event_likes")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "event_id")
    private int eventId;
    @Enumerated(EnumType.STRING)
    @Column(name = "like_type")
    private RatingType type;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}