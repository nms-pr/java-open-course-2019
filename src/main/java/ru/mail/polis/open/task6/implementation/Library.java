package ru.mail.polis.open.task6.implementation;

import ru.mail.polis.open.task6.implementation.people.Librarian;
import ru.mail.polis.open.task6.implementation.people.Manager;
import ru.mail.polis.open.task6.interfaces.BookProvider;
import ru.mail.polis.open.task6.interfaces.BookStorage;
import ru.mail.polis.open.task6.interfaces.LibraryForCustomer;
import ru.mail.polis.open.task6.interfaces.LibraryForLibrarian;
import ru.mail.polis.open.task6.interfaces.LibraryForManager;

public class Library implements LibraryForCustomer, LibraryForLibrarian, LibraryForManager {

    private BookShelf bookShelf;
    private Manager manager;
    private Librarian librarian;
    private boolean isOpened;

    public Library(BookShelf bookShelf, Manager manager, Librarian librarian) {

        this.bookShelf = bookShelf;
        this.manager = manager;
        this.librarian = librarian;
        isOpened = false;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public Librarian getLibrarian() {
        return librarian;
    }

    @Override
    public BookProvider getBookProvider() {
        return bookShelf;
    }

    @Override
    public BookStorage getBookStorage() {
        return bookShelf;
    }

    @Override
    public void open() {
        if (isOpened) {
            throw new IllegalStateException("Library is already opened");
        }

        isOpened = true;
    }

    @Override
    public void close() {
        if (!isOpened) {
            throw new IllegalStateException("Library is already closed");
        }

        isOpened = false;
    }

    @Override
    public boolean isOpened() {
        return isOpened;
    }
}
