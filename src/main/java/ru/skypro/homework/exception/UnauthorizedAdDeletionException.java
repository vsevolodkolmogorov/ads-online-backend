package ru.skypro.homework.exception;

public class UnauthorizedAdDeletionException extends RuntimeException {
    public UnauthorizedAdDeletionException(String message) {
        super(message);
    }
}