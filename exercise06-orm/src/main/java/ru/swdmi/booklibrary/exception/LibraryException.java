package ru.swdmi.booklibrary.exception;

public class LibraryException extends RuntimeException {
    public LibraryException(String message, Object... args) {
        super(String.format(message, args));
    }
}
