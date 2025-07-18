package com.victorto.telcoservice.exception;

// package com.telcoservice.exception;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String message,
        Instant timestamp,
        String path
) {
    public ErrorResponse(int status, String message, String path) {
        this(status, message, Instant.now(), path);
    }
}
