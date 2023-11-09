package ru.practicum.compilation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.NewCompilationDto;
import ru.practicum.compilation.dto.UpdateCompilationRequest;
import ru.practicum.compilation.mapper.CompilationMapper;
import ru.practicum.compilation.model.Compilation;
import ru.practicum.compilation.repository.CompilationRepository;
import ru.practicum.events.mapper.EventMapper;
import ru.practicum.events.model.Event;
import ru.practicum.events.service.EventService;
import ru.practicum.exceptions.DataNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventService eventService;
    private final CompilationMapper compilationMapper;
    private final EventMapper eventMapper;

    public CompilationDto prepareDto(Compilation compilation) {
        CompilationDto compiledDto = compilationMapper.toDto(compilation);
        Set<Event> events = compilation.getEvents();
        if (events != null && !events.isEmpty()) {
            compiledDto.setEvents(compilation.getEvents().stream().map(eventMapper::toShortDto).collect(Collectors.toList()));
        } else {
            compiledDto.setEvents(new ArrayList<>());
        }
        return compiledDto;
    }

    public Compilation prepareDao(NewCompilationDto newCompilationDto) {
        Compilation compiled = compilationMapper.fromDto(newCompilationDto);
        Set<Integer> eventIds = newCompilationDto.getEvents();
        if (eventIds != null && !eventIds.isEmpty()) {
            compiled.setEvents(eventService.findAllById(eventIds));
        } else {
            compiled.setEvents(new HashSet<>());
        }
        return compiled;
    }

    public Compilation findById(int compilationId) {
        Optional<Compilation> compiled = compilationRepository.findById(compilationId);

        if (compiled.isEmpty()) {
            throw new DataNotFoundException(Compilation.class.getName(), compilationId);
        }
        return compiled.get();
    }

    public CompilationDto getById(int compId) {
        Compilation find = findById(compId);
        return prepareDto(find);
    }

    public List<CompilationDto> getAll(Boolean pinned, int from, int size) {
        List<Compilation> result = compilationRepository.findAllByPinned(pinned, from, size);
        return result.stream()
                .map(this::prepareDto)
                .collect(Collectors.toList());
    }

    public CompilationDto save(NewCompilationDto newCompilationDto) {
        Compilation compiled = prepareDao(newCompilationDto);
        return prepareDto(compilationRepository.save(compiled));
    }

    public CompilationDto update(int compId, UpdateCompilationRequest updateCompilationRequest) {
        Compilation compiled = findById(compId);
        compilationMapper.update(updateCompilationRequest, compiled);
        Set<Integer> eventIds = updateCompilationRequest.getEvents();
        if (eventIds != null && !eventIds.isEmpty()) {
            compiled.setEvents(eventService.findAllById(eventIds));
        }
        return prepareDto(compilationRepository.save(compiled));
    }

    public void delete(int compId) {
        Compilation compiled = findById(compId);
        compilationRepository.delete(compiled);
    }
}