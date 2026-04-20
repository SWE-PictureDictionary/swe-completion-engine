package com.swe.project.completionengine.service;

import org.springframework.stereotype.Service;

import com.swe.project.completionengine.dto.CompletionRequest;
import com.swe.project.completionengine.dto.CompletionResponse;
import com.swe.project.completionengine.strategy.CompletionStrategy;

@Service
public class CompletionService {

    private final CompletionStrategy completionStrategy;

    public CompletionService(CompletionStrategy completionStrategy) {
        this.completionStrategy = completionStrategy;
    }

    public CompletionResponse checkCompletion(CompletionRequest request) {
        return completionStrategy.evaluate(
                request.getAllLabels(),
                request.getClickedLabels()
        );
    }
}