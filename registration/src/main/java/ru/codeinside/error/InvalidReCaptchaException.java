package ru.codeinside.error;

public class InvalidReCaptchaException extends RuntimeException {
    public InvalidReCaptchaException(final String message) {
        super(message);
    }
}