package ru.mail.polis.open.task9;

public class NotDoneBuildException extends RuntimeException {

    NotDoneBuildException() {
        super("something wrong");
    }

    NotDoneBuildException(String msg) {
        super(msg);
    }
}
