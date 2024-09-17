package com.metrobuzz.dependencies.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T> extends CustomResponse {

    private T payload;

    public Response(String message, int code, T payload) {
        super(message, code);
        this.payload = payload;
    }
}
