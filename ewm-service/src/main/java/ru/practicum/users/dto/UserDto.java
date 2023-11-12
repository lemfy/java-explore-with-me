package ru.practicum.users.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDto {
    int id;
    @NotBlank
    @Size(min = 2, max = 250)
    String name;
    @NotBlank
    @Size(min = 6, max = 254)
    @Email
    String email;
    private double rating;
}