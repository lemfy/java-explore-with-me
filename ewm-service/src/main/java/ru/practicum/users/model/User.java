package ru.practicum.users.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Formula(value = "(SELECT AVG(e.rating) " +
            "FROM events e " +
            "WHERE e.user_id = id)")
    private Double rating;
}