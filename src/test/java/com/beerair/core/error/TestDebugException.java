package com.beerair.core.error;

public class TestDebugException extends RuntimeException {
    public TestDebugException() {
        super();
    }

    public TestDebugException(String message) {
        super(message);
    }

    public TestDebugException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestDebugException(Throwable cause) {
        super(cause);
    }

    protected TestDebugException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
