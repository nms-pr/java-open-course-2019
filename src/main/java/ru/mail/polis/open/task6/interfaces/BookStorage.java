package ru.mail.polis.open.task6.interfaces;

import ru.mail.polis.open.task6.implementation.Book.Book;

public interface BookStorage {

    void addBook(Book book);
    boolean removeBook(Book book);
}
