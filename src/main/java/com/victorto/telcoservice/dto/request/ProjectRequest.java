package com.victorto.telcoservice.dto.request;

import com.victorto.telcoservice.model.ProjectPriority;
import com.victorto.telcoservice.model.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ProjectRequest(
    @NotBlank @Size(max = 100)
    String name,
    
    @Size(max = 500)
    String description,
    
    @NotNull
    ProjectType projectType,
    
    @NotNull
    ProjectPriority priority,
    
    LocalDate startDate,
    
    LocalDate targetCompletionDate
) {}
