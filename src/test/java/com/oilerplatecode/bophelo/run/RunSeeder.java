package com.oilerplatecode.bophelo.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class RunSeeder {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static List<Run> loadRuns() throws Exception {
        try (InputStream is = RunSeeder.class.getResourceAsStream("/data/runs.json")) {
// TODO: go deeper in how to read files in Java
            return objectMapper.readValue(is, new TypeReference<List<Run>>() {});
        }
    }

    static void seedDatabase(List<Run> runs) throws Exception {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/bophelo", "mbuzumbuzu", "secret")) {
            String sql = "INSERT INTO run (title, started_on, completed_on, meters, location) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Run run : runs) {
                    ps.setString(1, run.getTitle());
                    ps.setObject(2, run.getStartedOn());
                    ps.setObject(3, run.getCompletedOn());
                    ps.setInt(4, run.getMeters());
                    ps.setString(5, run.getLocation().name());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }
}
