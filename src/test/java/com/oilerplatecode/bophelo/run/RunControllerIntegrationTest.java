package com.oilerplatecode.bophelo.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RunControllerIntegrationTest {
    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder().baseUrl("http://localhost:8085").build();
    }

    @Test
    void shouldFindAllRuns() {
        List<Run> runs = restClient.get()
                .uri("/api/runs")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Run>>() {});

        assertNotNull(runs);
        assertEquals(10, runs.size());
    }

    @Test
    void shouldFindRunById() {
        Run run = restClient.get()
                .uri("/api/runs/5")
                .retrieve()
                .body(new ParameterizedTypeReference<Run>() {});

        assertNotNull(run);
        System.out.println(run);
        assertEquals(5, run.id());
        assertEquals("Sunset Jog", run.title());
        assertEquals(Location.INDOOR, run.location());
    }

    @Test
    void shouldCreateRun() {
        Run newRun = new Run(
                11,
                "Int Test run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3000,
                Location.OUTDOOR,
                null
        );

        ResponseEntity<Void> response = restClient.post()
                .uri("/api/runs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newRun)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void shouldUpdateRun() {
        // get the verion of the run becuse of Optimistic lock exception on saving entity of type
        Run existingRun = restClient.get()
                .uri("/api/runs/11")
                .retrieve()
                .body(new ParameterizedTypeReference<Run>() {});

        assertNotNull(existingRun);
        int version = existingRun.version();

        Run run = new Run(
                11,
                "Updating run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                5000,
                Location.OUTDOOR,
                version + 1
        );

        ResponseEntity<Void> response = restClient.put()
                .uri("/api/runs/11")
                .contentType(MediaType.APPLICATION_JSON)
                .body(run)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void shouldDeleteRun() {
        ResponseEntity<Void> response = restClient.delete()
                .uri("/api/runs/11")
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }
}