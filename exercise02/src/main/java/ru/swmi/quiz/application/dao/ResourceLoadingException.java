package ru.swmi.quiz.application.dao;

public class ResourceLoadingException extends RuntimeException {
    public ResourceLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
