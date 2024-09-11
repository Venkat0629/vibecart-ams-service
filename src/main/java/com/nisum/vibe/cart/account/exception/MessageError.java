package com.nisum.vibe.cart.account.exception;

import java.time.Instant;

public class MessageError {
    public String errorMessage;
    private Instant time;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }
}
