package com.metrobuzz.dependencies.constants;

import lombok.Data;

@Data
public class ResponseConstants {
    private String message;

    public ResponseConstants(int code) {
        this.message = getMessageFromCode(code);
    }

    public static String getMessageFromCode(int code) {
        switch (code) {
            case 200:
                return "Successfully treated your request";
            case 404:
                return "Unable to find this resource";
            default:
                return "Internal server error";
        }
    }
}
