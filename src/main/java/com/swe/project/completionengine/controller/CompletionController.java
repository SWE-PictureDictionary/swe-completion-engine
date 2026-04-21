package com.swe.project.completionengine.controller;

import org.springframework.web.bind.annotation.*;

import com.swe.project.completionengine.client.ContentManagerClient;
import com.swe.project.completionengine.dto.CompletionRequest;
import com.swe.project.completionengine.dto.CompletionResponse;
import com.swe.project.completionengine.service.CompletionService;

@RestController
@RequestMapping("/completion")
public class CompletionController {

    private final ContentManagerClient contentManagerClient;
    private final CompletionService completionService;

    public CompletionController(ContentManagerClient contentManagerClient, CompletionService completionService) {
        this.contentManagerClient = contentManagerClient;
        this.completionService = completionService;
    }

    @PostMapping("/check")
    public CompletionResponse checkCompletion(@RequestBody CompletionRequest request) {
        return completionService.checkCompletion(request);
    }
}