package com.oilerplatecode.bophelo.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/sanity-check")
    String systemCheck() {
        return "Hello runner";
    }

    @GetMapping("/1")
    Run findById() {
        return runRepository.findById(1);
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }
}
