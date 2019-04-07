package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Guest;

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
        List<Book> booksForOrder;
        if (order == null || order.getGetWantedBookNames() == null
            && order.getWantedGenres() == null) {
            throw new IllegalArgumentException();
        } else if (order.getGetWantedBookNames() == null) {
            booksForOrder = bookStorage
                .getBooksForGenre(order.getWantedGenres());
        } else if (order.getWantedGenres() == null) {
            booksForOrder = bookStorage
                .getBooksForName(order.getGetWantedBookNames());
        } else {
            booksForOrder = bookStorage
                .getBooksForGenreAndName(order.getGetWantedBookNames(),order.getWantedGenres());
        }
        return booksForOrder;
    }
}
