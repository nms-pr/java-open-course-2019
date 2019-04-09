package ru.mail.polis.open.task6;

public class ClosedLibraryExeption extends ExceptionInInitializerError {
    ClosedLibraryExeption(String message) {
        super(message);
    }

    ClosedLibraryExeption() {
        super("Library is closed");
    }
}
