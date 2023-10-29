package ru.practicum.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.practicum.EndpointDto;
import ru.practicum.model.Endpoint;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T23:09:52+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Amazon.com Inc.)"
)
@Component
public class EndpointMapperImpl implements EndpointMapper {

    @Override
    public EndpointDto toDto(Endpoint endpointHit) {
        if ( endpointHit == null ) {
            return null;
        }

        EndpointDto.EndpointDtoBuilder endpointDto = EndpointDto.builder();

        endpointDto.app( endpointHit.getApp() );
        endpointDto.uri( endpointHit.getUri() );
        endpointDto.ipAddress( endpointHit.getIpAddress() );
        endpointDto.timestamp( endpointHit.getTimestamp() );

        return endpointDto.build();
    }

    @Override
    public Endpoint fromDto(EndpointDto endpointDto) {
        if ( endpointDto == null ) {
            return null;
        }

        Endpoint.EndpointBuilder endpoint = Endpoint.builder();

        endpoint.app( endpointDto.getApp() );
        endpoint.uri( endpointDto.getUri() );
        endpoint.ipAddress( endpointDto.getIpAddress() );
        endpoint.timestamp( endpointDto.getTimestamp() );

        return endpoint.build();
    }
}
