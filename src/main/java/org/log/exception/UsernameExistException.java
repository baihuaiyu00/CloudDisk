package org.log.exception;

public class UsernameExistException extends Exception {

    public UsernameExistException() {

    }

    public UsernameExistException(String message) {
        super(message);

    }

    public UsernameExistException(Throwable cause) {
        super(cause);

    }

    public UsernameExistException(String message, Throwable cause) {
        super(message, cause);

    }

    public UsernameExistException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

}
