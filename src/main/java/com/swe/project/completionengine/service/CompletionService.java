package com.swe.project.completionengine.service;

import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.project.completionengine.client.ContentManagerClient;
import com.swe.project.completionengine.dto.CompletionRequest;
import com.swe.project.completionengine.dto.CompletionResponse;
import com.swe.project.completionengine.strategy.CompletionStrategy;

@Service
public class CompletionService {

    private final ContentManagerClient contentManagerClient;
    private final CompletionStrategy completionStrategy;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletionService(ContentManagerClient contentManagerClient, CompletionStrategy completionStrategy) {
        this.contentManagerClient = contentManagerClient;
        this.completionStrategy = completionStrategy;
    }

    public CompletionResponse checkCompletion(CompletionRequest request) {
        return completionStrategy.evaluate(
                request.getAllLabels(),
                request.getClickedLabels()
        );
    }

    public Set<String> fetchHotspotLabels(String topicId) {
        
        ResponseEntity<String> response = contentManagerClient.getTopic(topicId);
        Set<String> labels = Set.of();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode hotspots = root.path("hotspots");
            if (hotspots.isMissingNode() || !hotspots.isArray()) {
                return Set.of(); // No labels found
            } else {
                for (JsonNode hotspot : hotspots) {
                    String label = hotspot.path("label").asText(null);
                    if (label != null && !label.isEmpty()) {
                        labels = Set.copyOf(labels);
                        labels.add(label);
                    }
                }
                return labels;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Set.of(); // Return empty set on error
        }
        
    }
}