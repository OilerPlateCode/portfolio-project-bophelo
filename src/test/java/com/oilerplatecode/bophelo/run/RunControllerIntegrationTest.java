package com.oilerplatecode.bophelo.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RunControllerIntegrationTest {
@LocalServerPort
    private int randomServerPort;
RestClient restClient;

@BeforeEach
    void setUp() {
    restClient = RestClient.create("https://localhost:" + randomServerPort);
}

    @Test
    void shouldFindAllRuns() {
List<Run> runs = restClient.get()
        .uri("/api/runs")
        .retrieve()
        .body(new ParameterizedTypeReference<List<Run>>() {
        });
assertEquals(10, runs.size());
    }
}