package ru.mail.polis.open.task6;

import java.util.Collection;

public interface ManagableLibrary {
    void addToLibrary(Collection<Book> books) throws LibraryException;

    void addToLibrary(Book book) throws LibraryException;

    void remove(Book book) throws LibraryException;

    void open();

    void close();
}
