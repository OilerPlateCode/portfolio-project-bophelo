package com.oilerplatecode.bophelo.run;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        return runRepository.findById(id);
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }
}
