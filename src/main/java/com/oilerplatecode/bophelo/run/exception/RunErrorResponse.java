package com.oilerplatecode.bophelo.run.exception;

public class RunErrorResponse {
    private final String message;

    public RunErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
