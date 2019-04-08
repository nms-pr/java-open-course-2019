package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.office.Book;
import ru.mail.polis.open.task6.office.GuestOrder;
import ru.mail.polis.open.task6.office.Librarian;

import java.util.List;

public abstract class Guest {

    private Librarian librarian;
    private boolean isWarned;

    Guest(Librarian librarian) {
        this.librarian = librarian;
        isWarned = false;
    }

    //choose from assortment
    abstract List<Book> processAvailableBooks(List<Book> availableBooks);

    abstract GuestOrder makeAnOrder();

    List<Book> askTheLibrarianAboutOrder(GuestOrder order) {
        if (librarian.libraryIsOpen()) {
            return librarian.lookForAvailableBooks(order);
        } else {
            return null;
        }
    }

    public void getWarned() {
        isWarned = true;
    }

    public void returnTheBooks(GuestOrder order) {
        if (order.getOwner() == this) {
            librarian.receiveReturnedBooks(order);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
