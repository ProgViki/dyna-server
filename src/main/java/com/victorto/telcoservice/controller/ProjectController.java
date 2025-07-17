package com.victorto.telcoservice.controller;

import com.victorto.telcoservice.dto.request.ProjectRequest;
import com.victorto.telcoservice.dto.response.ProjectResponse;
import com.victorto.telcoservice.model.ProjectStatus;
import com.victorto.telcoservice.model.User;
import com.victorto.telcoservice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "Create a new project")
    public ResponseEntity<ProjectResponse> createProject(
            @RequestBody ProjectRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.createProject(request, user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    @Operation(summary = "Get all projects")
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update project status")
    public ResponseEntity<ProjectResponse> updateProjectStatus(
            @PathVariable Long id,
            @RequestParam ProjectStatus status,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(projectService.updateProjectStatus(id, status, user));
    }
}