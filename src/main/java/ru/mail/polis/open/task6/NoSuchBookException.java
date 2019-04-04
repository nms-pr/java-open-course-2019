package ru.mail.polis.open.task6;

public class NoSuchBookException extends RuntimeException {
    NoSuchBookException() {
        super("Something wrong");
    }

    NoSuchBookException(String msg) {
        super(msg);
    }
}
