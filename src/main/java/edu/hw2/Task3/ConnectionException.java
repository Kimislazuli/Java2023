package edu.hw2.Task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Exception exception) {
        super(message, exception);
    }
}
