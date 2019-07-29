package com.example.tvordering.exceptions;

public class TvorderingNotFoundException extends TvorderingRuntimeException {

    public TvorderingNotFoundException(String message) {
        super(message);
    }

    public TvorderingNotFoundException(String message, Throwable t) {
        super(message, t);
    }

}
