package com.metrobuzz.dependencies.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private boolean status;
    private int code;
    private String message;
    private T payload;

    public Response(String message, int code, T payload) {
        this.status = this.getCodeStatus(code);
        this.code = code;
        this.payload = payload;
        this.message = message;
    }

    private boolean getCodeStatus(int code) {
        String codeString = Integer.toString(code);
        return codeString.contains("20");
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
