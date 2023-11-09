package ru.practicum.client;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.practicum.EndpointDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatClientImpl implements StatClient {
    private final String serverURL;
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public StatClientImpl(@Value("${stats-server.url}") String serverUrl) {
        this.serverURL = serverUrl;
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
    }

    private void prepareHeader() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Override
    public ResponseEntity<String> saveHit(String app, String uri, String ip, LocalDateTime timestamp) {
        prepareHeader();

        EndpointDto endpointDto = EndpointDto.builder()
                .app(app)
                .uri(uri)
                .ip(ip)
                .timestamp(timestamp)
                .build();

        HttpEntity<EndpointDto> entity = new HttpEntity<>(endpointDto, headers);

        return this.restTemplate.postForEntity(
                serverURL + "/hit",
                entity,
                String.class);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Object> getStats(LocalDateTime start, LocalDateTime end, List<String> uris) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        prepareHeader();
        Map<String, Object> params = new HashMap<>();
        params.put("start", start.format(DateTimeFormatter.ofPattern(dateFormat)));
        params.put("end", end.format(DateTimeFormatter.ofPattern(dateFormat)));
        params.put("uris", String.join(",", uris));
        params.put("unique", true);

        HttpEntity request = new HttpEntity(headers);

        return restTemplate.exchange(
                serverURL + "/stats?start={start}&end={end}&uris={uris}&unique={unique}",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {
                },
                params
        );
    }
}