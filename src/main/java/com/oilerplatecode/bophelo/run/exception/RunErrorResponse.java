package com.oilerplatecode.bophelo.run.exception;

public class RunErrorResponse {
    private final String message;
    private final String path;


    public RunErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
