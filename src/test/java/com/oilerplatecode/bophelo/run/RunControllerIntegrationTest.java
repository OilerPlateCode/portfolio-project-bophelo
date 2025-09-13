package com.oilerplatecode.bophelo.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

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
}