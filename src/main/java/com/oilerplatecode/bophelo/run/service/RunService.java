package com.oilerplatecode.bophelo.run.service;

import com.oilerplatecode.bophelo.run.Run;
import com.oilerplatecode.bophelo.run.RunRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunService {
    RunRepository runRepository;

    List<Run> findAll() {
        return runRepository.findAll();
    }
}
