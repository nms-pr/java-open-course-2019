package ru.mail.polis.open.task6.LibraryOffice;

import ru.mail.polis.open.task6.Guest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Librarian {

    private LibraryStorage bookStorage;
    private HashMap<Guest, GuestOrder> ledger;

    Librarian(LibraryStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    public boolean libraryIsOpen() {
        return bookStorage.isOpen();
    }

    public List<Book> checkBooksInTheStore(GuestOrder order) {
        return new ArrayList<>();//TODO()
    }
}
