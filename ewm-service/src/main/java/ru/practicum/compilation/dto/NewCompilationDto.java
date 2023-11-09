package ru.practicum.compilation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
public class NewCompilationDto {
    @NotBlank
    @Size(min = 2, max = 50)
    private String title;
    private Set<Integer> events;
    private boolean pinned;
}