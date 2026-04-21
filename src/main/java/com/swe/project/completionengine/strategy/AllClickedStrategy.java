package com.swe.project.completionengine.strategy;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.swe.project.completionengine.dto.CompletionResponse;

@Component
public class AllClickedStrategy implements CompletionStrategy {

    @Override
    public CompletionResponse evaluate(Set<String> allLabels, Set<String> clickedLabels) {
        Set<String> remaining = new HashSet<>(allLabels);
        remaining.removeAll(clickedLabels);
        
        return remaining.isEmpty() ? CompletionResponse.complete() : CompletionResponse.inProgress(remaining.size());
    }
}