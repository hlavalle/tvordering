package com.example.tvordering.exceptions;

class TvorderingRuntimeException extends RuntimeException {
    TvorderingRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    TvorderingRuntimeException(String message) {
        super(message);
    }
}
