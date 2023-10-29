package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.EndpointDto;
import ru.practicum.model.Endpoint;

@Mapper
public interface EndpointMapper {
    Endpoint fromDto(EndpointDto endpointHitDto);
}