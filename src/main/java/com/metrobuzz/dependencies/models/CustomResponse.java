package com.metrobuzz.dependencies.models;

import lombok.Data;

@Data
public class CustomResponse {
    private boolean status;
    private int code;
    private String message;

    public CustomResponse(String message, int code) {
        this.status = this.getCodeStatus(code);
        this.code = code;
        this.message = message;
    }

    private boolean getCodeStatus(int code) {
        String codeString = Integer.toString(code);
        return codeString.contains("20");
    }
}