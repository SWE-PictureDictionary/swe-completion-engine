package com.swe.project.completionengine.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swe.project.completionengine.client.ContentManagerClient;
import com.swe.project.completionengine.dto.CompletionRequest;
import com.swe.project.completionengine.dto.CompletionResponse;
import com.swe.project.completionengine.strategy.CompletionStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompletionService {

    private final ContentManagerClient contentManagerClient;
    private final CompletionStrategy completionStrategy;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletionService(ContentManagerClient contentManagerClient,
                             CompletionStrategy completionStrategy) {
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
        Set<String> labels = new HashSet<>();

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode hotspots = root.path("hotspots");

            if (!hotspots.isArray()) {
                return labels;
            }

            for (JsonNode hotspot : hotspots) {
                String label = hotspot.path("label").asText(null);

                if (label != null && !label.isBlank()) {
                    labels.add(label);
                }
            }

            return labels;
        } catch (Exception e) {
            e.printStackTrace();
            return Set.of();
        }
    }
}