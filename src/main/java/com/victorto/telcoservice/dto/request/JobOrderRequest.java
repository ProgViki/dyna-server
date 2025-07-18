package com.victorto.telcoservice.dto.request;

// package com.telcoservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record JobOrderRequest(
        @NotNull Long projectId,
        Long surveyId,
        @NotBlank @Size(max = 500) String workDescription,
        @Size(max = 1000) String requiredMaterials,
        Double estimatedHours,
        String assignedTeam,
        List<JobOrderDeviceRequest> devices
) {}

record JobOrderDeviceRequest(
        @NotNull Long deviceId,
        @Positive int quantity,
        String purpose
) {}