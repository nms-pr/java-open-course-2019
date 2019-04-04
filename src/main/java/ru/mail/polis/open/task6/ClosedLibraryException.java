package ru.mail.polis.open.task6;

public class ClosedLibraryException extends RuntimeException {
    ClosedLibraryException() {
        super("Something wrong");
    }

    ClosedLibraryException(String msg) {
        super(msg);
    }
}
