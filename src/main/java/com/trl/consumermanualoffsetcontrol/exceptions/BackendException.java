package com.trl.consumermanualoffsetcontrol.exceptions;

public class BackendException extends RuntimeException {

    public BackendException() {
    }

    public BackendException(String message) {
        super(message);
    }

    public BackendException(String message, Throwable cause) {
        super(message, cause);
    }

    public BackendException(Throwable cause) {
        super(cause);
    }

}
