package ru.mail.polis.open.task6.library;

import java.util.List;

public class Library {
    private Manager manager;
    private Librarian librarian;
    private Shelf shelf = new Shelf();
    private boolean isOpen = true;

    public Library(Manager manager, Librarian librarian) {
        this.manager = manager;
        this.librarian = librarian;
    }

    public boolean isOpen() {
        return isOpen;
    }

    Shelf getShelf() {
        return shelf;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    void open() {
        isOpen = true;
    }

    void close() {
        isOpen = false;
    }

    public List<Book> getAvailableBooks() {
        return shelf.availableBooks();
    }
}
