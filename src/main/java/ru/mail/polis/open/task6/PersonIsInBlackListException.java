package ru.mail.polis.open.task6;

public class PersonIsInBlackListException extends RuntimeException {
    PersonIsInBlackListException() {
        super("Something wrong");
    }

    PersonIsInBlackListException(String msg) {
        super(msg);
    }
}
