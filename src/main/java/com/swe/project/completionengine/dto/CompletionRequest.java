package com.swe.project.completionengine.dto;

import java.util.Set;

public class CompletionRequest {
    private Set<String> allLabels;
    private Set<String> clickedLabels;

    public CompletionRequest() {
    }

    public CompletionRequest(Set<String> allLabels, Set<String> clickedLabels) {
        this.allLabels = allLabels;
        this.clickedLabels = clickedLabels;
    }

    public Set<String> getAllLabels() {
        return allLabels;
    }

    public void setAllLabels(Set<String> allLabels) {
        this.allLabels = allLabels;
    }

    public Set<String> getClickedLabels() {
        return clickedLabels;
    }

    public void setClickedLabels(Set<String> clickedLabels) {
        this.clickedLabels = clickedLabels;
    }
}