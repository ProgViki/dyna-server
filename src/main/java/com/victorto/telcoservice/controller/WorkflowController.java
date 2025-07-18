package com.victorto.telcoservice.controller;

// package com.telcoservice.controller;

import com.telcoservice.dto.response.WorkflowStepResponse;
import com.telcoservice.service.WorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class WorkflowController {

    private final WorkflowService workflowService;

    @GetMapping("/{entityType}/{entityId}")
    @Operation(summary = "Get workflow steps for an entity")
    public ResponseEntity<List<WorkflowStepResponse>> getWorkflowSteps(
            @PathVariable String entityType,
            @PathVariable Long entityId) {
        return ResponseEntity.ok(workflowService.getWorkflowSteps(entityType, entityId));
    }

    @PostMapping("/steps/{stepId}/complete")
    @Operation(summary = "Complete a workflow step")
    public ResponseEntity<WorkflowStepResponse> completeWorkflowStep(
            @PathVariable Long stepId,
            @RequestParam boolean approved,
            @RequestParam(required = false) String comments,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                workflowService.completeStep(stepId, approved, comments, user));
    }
}
