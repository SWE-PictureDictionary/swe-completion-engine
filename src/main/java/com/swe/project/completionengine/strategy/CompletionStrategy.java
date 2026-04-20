package com.swe.project.completionengine.strategy;

import java.util.Set;

import com.swe.project.completionengine.dto.CompletionResponse;

public interface CompletionStrategy {

    CompletionResponse evaluate(Set<String> allLabels, Set<String> clickedLabels);
}