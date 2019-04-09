package ru.mail.polis.open.task6;

public interface Visitor {
    void takeBook(String name, Library library);

    void returnBook(Book book);

    void notifyAboutBook(Book book);
}
