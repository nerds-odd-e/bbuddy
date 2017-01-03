package com.odde.bbuddy.common.view;

public class Result {
    private final boolean success;
    private final String message;

    private Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public static Result success(String message) {
        return new Result(true, message);
    }

    public String getMessage() {
        return message;
    }

    public static Result failed(String message) {
        return new Result(false, message);
    }
}
