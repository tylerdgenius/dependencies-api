package com.metrobuzz.dependencies.models;

import lombok.Data;

@Data
public class ErrorResponse {
    private String trace;
    private String errorMessage;

    public ErrorResponse(String trace, String message) {
        this.trace = trace;
        this.errorMessage = message;
    }
}
