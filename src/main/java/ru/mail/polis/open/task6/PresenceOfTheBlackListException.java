package ru.mail.polis.open.task6;

public class PresenceOfTheBlackListException extends RuntimeException {
    PresenceOfTheBlackListException() {
        super("Something wrong");
    }

    PresenceOfTheBlackListException(String msg) {
        super(msg);
    }
}
