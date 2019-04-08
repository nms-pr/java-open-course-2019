package ru.mail.polis.open.task6;

public interface Visitor {
    void giveBooksBack() throws LibraryException;

    void takeBookOut(String title) throws LibraryException;

    String getCredentials();

    void notifyAboutBooks();
}
