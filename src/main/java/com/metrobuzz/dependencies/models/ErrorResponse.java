package com.metrobuzz.dependencies.models;

import lombok.Data;

@Data
public class ErrorResponse {
    private String trace;
    private String errorMessage;

    public ErrorResponse(String trace, String errorMessage) {
        this.errorMessage = errorMessage;
        this.trace = trace;
    }
}
