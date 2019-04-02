package ru.mail.polis.open.task6;

public class NoSpaceForBookException extends RuntimeException {
    NoSpaceForBookException() {
        super("Something wrong");
    }

    NoSpaceForBookException(String msg) {
        super(msg);
    }
}
