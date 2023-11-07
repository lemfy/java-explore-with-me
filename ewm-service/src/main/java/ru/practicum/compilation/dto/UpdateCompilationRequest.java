package ru.practicum.compilation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
@RequiredArgsConstructor
public class UpdateCompilationRequest {
    @Size(min = 2, max = 50)
    private String title;
    private boolean pinned;
    private Set<Integer> events;
}