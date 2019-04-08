package ru.mail.polis.open.task6;

import java.util.Collection;

public interface Manager {
    void addBook(Book book);

    void addBooks(Collection<Book> books);

    void removeBook(Book book);

    void openLibrary();

    void closeLibrary();
}
