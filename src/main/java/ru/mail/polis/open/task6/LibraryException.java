package ru.mail.polis.open.task6;

public class LibraryException extends Exception {
    String message;

    LibraryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
