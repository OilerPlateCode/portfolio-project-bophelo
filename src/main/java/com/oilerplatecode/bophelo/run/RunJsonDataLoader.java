package com.oilerplatecode.bophelo.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader  implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final  RunRepository runRepository;
    private final ObjectMapper objectMapper;
    private final JdbcClient jdbcClient;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper, JdbcClient jdbcClient) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data abd savibg to in-memory collection", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IIOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Runs from JSON data because the collection contains data");
        }
    }
}
