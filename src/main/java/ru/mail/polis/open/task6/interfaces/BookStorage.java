package ru.mail.polis.open.task6.interfaces;

import ru.mail.polis.open.task6.implementation.book.Book;

public interface BookStorage {

    void addBook(Book book);

    boolean removeBook(Book book);

    boolean removeAllBookInstances(Book book);
}
