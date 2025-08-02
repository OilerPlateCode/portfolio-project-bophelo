package com.oilerplatecode.bophelo.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    @PostConstruct
    private void init() {
        runs.add(
                new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5000, Location.OUTDOOR)
        );
        runs.add(
                new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.MINUTES), 3000, Location.INDOOR)
        );
    }
}
