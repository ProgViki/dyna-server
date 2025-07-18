package com.victorto.telcoservice.controller;

// package com.telcoservice.controller;

import com.telcoservice.dto.request.JobOrderRequest;
import com.telcoservice.dto.response.JobOrderResponse;
import com.telcoservice.service.JobOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-orders")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class JobOrderController {

    private final JobOrderService jobOrderService;

    @PostMapping
    @Operation(summary = "Create a new job order")
    public ResponseEntity<JobOrderResponse> createJobOrder(
            @Valid @RequestBody JobOrderRequest request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(jobOrderService.createJobOrder(request, user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get job order by ID")
    public ResponseEntity<JobOrderResponse> getJobOrder(@PathVariable Long id) {
        return ResponseEntity.ok(jobOrderService.getJobOrderById(id));
    }

    @GetMapping
    @Operation(summary = "Get all job orders")
    public ResponseEntity<List<JobOrderResponse>> getAllJobOrders() {
        return ResponseEntity.ok(jobOrderService.getAllJobOrders());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update job order status")
    public ResponseEntity<JobOrderResponse> updateJobOrderStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(jobOrderService.updateJobOrderStatus(id, status, user));
    }
}