package ru.practicum.users.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserShortDto {
    private int id;
    @NotBlank
    @Size(min = 2, max = 250)
    private String name;
}