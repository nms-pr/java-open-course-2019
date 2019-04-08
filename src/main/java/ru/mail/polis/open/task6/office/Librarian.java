package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Guest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Librarian {

    private LibraryStorage bookStorage;
    private HashMap<Guest, GuestOrder> ledger;

    Librarian(LibraryStorage bookStorage) {
        this.bookStorage = bookStorage;
        ledger = new HashMap<>();
    }

    public boolean libraryIsOpen() {
        return bookStorage.isOpen();
    }

    public List<Book> lookForAvailableBooks(GuestOrder order) {
        return findInStorage(order);
    }

    public void giveBooks(List<Book> books, GuestOrder order) {
        ledger.put(order.getOwner(), order);
        order.setTakenBooks(books);
        bookStorage.removeBook(books);
    }

    private List<Book> findInStorage(GuestOrder order) {
        List<Book> availableBooks;
        if (order == null || order.getGetWantedBookNames() == null
            && order.getWantedGenres() == null) {
            throw new IllegalArgumentException();
        } else if (order.getGetWantedBookNames() == null) {
            availableBooks = bookStorage
                .getBooksForGenre(order.getWantedGenres());
        } else if (order.getWantedGenres() == null) {
            availableBooks = bookStorage
                .getBooksForName(order.getGetWantedBookNames());
        } else {
            availableBooks = bookStorage
                .getBooksForGenreAndName(order.getGetWantedBookNames(),order.getWantedGenres());
        }
        return availableBooks;
    }

    void checkDebts() {
        for (Map.Entry<Guest, GuestOrder> ledgerNote : ledger.entrySet()) {
            LocalDateTime orderDate = ledgerNote.getValue().getOrderDate();
            if (orderDate.compareTo(LocalDateTime.now().minusMonths(1)) < 0) {
                Guest guest = ledgerNote.getKey();
                sendMessageToGuest(guest);
            }
        }
    }

    private void sendMessageToGuest(Guest guest) {
        guest.getWarned();
    }

    public void receiveReturnedBooks(GuestOrder order) {
        bookStorage.putBook(order.getTakenBooks());
        ledger.remove(order.getOwner(), order);
    }
}
