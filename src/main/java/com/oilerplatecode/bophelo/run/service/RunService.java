package com.oilerplatecode.bophelo.run.service;

import com.oilerplatecode.bophelo.run.Run;
import com.oilerplatecode.bophelo.run.RunRepository;
import com.oilerplatecode.bophelo.run.exception.RunNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RunService {
    private final RunRepository runRepository;

    public RunService(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public List<Run> findAll() {
        return runRepository.findAll();
    }

    public Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);

        if (run.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            throw new RunNotFoundException();
        }
        return run.get(); // we had this get in the repo when it was not options
    }

    public void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }

    public void update(@RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    public void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).get());
    }
}
