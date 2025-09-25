package com.oilerplatecode.bophelo.run;

import org.junit.jupiter.api.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RunControllerIntegrationTest {
    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.builder().baseUrl("http://localhost:8085").build();
    }

    @Order(1)
    @Test
    void shouldFindAllRuns() {
        List<Run> runs = restClient.get()
                .uri("/api/runs")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Run>>() {});

        assertNotNull(runs);
        assertEquals(runs.size(), runs.size());
    }

    @Order(2)
    @Test
    void shouldFindRunById() {
        Run run = restClient.get()
                .uri("/api/runs/5")
                .retrieve()
                .body(new ParameterizedTypeReference<Run>() {});

        assertNotNull(run);
        System.out.println(run);
        assertEquals(5, run.getId());
        assertEquals("Sunset Jog", run.getTitle());
        assertEquals(Location.INDOOR, run.getLocation());
    }

    @Order(3)
    @Test
    void shouldCreateRun() {
        Run newRun = new Run(
                "Int Test run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3000,
                Location.OUTDOOR
        );

        ResponseEntity<Void> response = restClient.post()
                .uri("/api/runs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newRun)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Order(4)
    @Test
    void shouldUpdateRun() {
        // get the version of the run because of Optimistic lock exception on saving entity of type
        // TODO: will spin up a testing environment using docker containers or Test containers

        Run run = new Run(
                "Updating run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                5000,
                Location.OUTDOOR
        );

        ResponseEntity<Void> response = restClient.put()
                .uri("/api/runs/11")
                .contentType(MediaType.APPLICATION_JSON)
                .body(run)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Order(5)
    @Test
    void shouldDeleteRun() {
        List<Run> runs = restClient.get()
                .uri("/api/runs")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Run>>() {});

        assertNotNull(runs);
        int lastIndex = runs.size() - 1;

        ResponseEntity<Void> response = restClient.delete()
                .uri("/api/runs/{id}", lastIndex)
                .retrieve()
                .toBodilessEntity();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // the below tests fail as i need to implement proper error handling
    @Order(6)
    @Test
    void shouldShowErrorIfRunNotFound() {


        var ex = assertThrows(HttpClientErrorException.NotFound.class, () ->
                restClient.get()
                        .uri("/api/runs/{id}", 16)
                        .retrieve()
                        .toBodilessEntity()
        );

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertTrue(ex.getResponseBodyAsString().contains("Not Found"));
        assertTrue(ex.getMessage().contains("Run Not Found"));
        assertEquals("404 Not Found: \"{\"message\":\"Run Not Found\",\"path\":\"/api/runs/16\"}\"", ex.getMessage());
    }

    @Order(7)
    @Test
    void shouldShowErrorIfRunHasNoTitle() {
        Run newRun = new Run(
                "",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3000,
                Location.OUTDOOR
        );

        var exception = assertThrows(HttpClientErrorException.BadRequest.class, () ->
                restClient.post()
                        .uri("/api/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(newRun)
                        .retrieve()
                        .toBodilessEntity()
                );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertTrue(exception.getResponseBodyAsString().contains("NotBlank"));
    }
}