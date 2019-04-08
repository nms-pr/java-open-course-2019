package ru.mail.polis.open.task6;

public interface Librarian {
    void takeBack(Book book) throws LibraryException;

    Book giveOut(String title, Visitor visitor) throws LibraryException;

    void notifyTakers();
}
