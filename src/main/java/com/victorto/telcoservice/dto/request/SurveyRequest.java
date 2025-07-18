package com.victorto.telcoservice.dto.request;

// package com.telcoservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SurveyRequest(
        @NotNull Long projectId,
        @NotBlank String siteLocation,
        String gpsCoordinates,
        @NotNull LocalDate surveyDate,
        @NotBlank String findings,
        String recommendations
) {}
