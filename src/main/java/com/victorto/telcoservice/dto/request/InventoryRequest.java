package com.victorto.telcoservice.dto.request;

// package com.telcoservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InventoryRequest(
        @NotNull Long deviceId,
        @Positive int quantity,
        String warehouseLocation,
        String binNumber,
        String referenceNumber,
        String notes
) {}
