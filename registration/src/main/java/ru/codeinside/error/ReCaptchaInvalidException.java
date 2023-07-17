package ru.codeinside.error;

public class ReCaptchaInvalidException extends RuntimeException {
    public ReCaptchaInvalidException(final String message) {
        super(message);
    }
}