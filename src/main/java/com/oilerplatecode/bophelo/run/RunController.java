package com.oilerplatecode.bophelo.run;

import com.oilerplatecode.bophelo.run.exception.RunNotFoundException;
import com.oilerplatecode.bophelo.run.service.RunService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunService runService;

    public RunController( RunService runService) {
        this.runService = runService;
    }

    @GetMapping("/sanity-check")
    String systemCheck() {
        return "Hello runner";
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        return this.runService.findById(id);
    }

    @GetMapping("")
    List<Run> findAll() {
        return runService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runService.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id) {
        runService.update(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runService.delete(runService.findById(id).getId());
    }
}

