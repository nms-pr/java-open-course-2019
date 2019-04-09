package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.office.Book;
import ru.mail.polis.open.task6.office.GuestOrder;
import ru.mail.polis.open.task6.office.Librarian;

import java.util.List;

public abstract class Guest {

    private Librarian librarian;
    private boolean isWarned;
    private List<Book> takenBooks;

    public Guest(Librarian librarian) {
        this.librarian = librarian;
        isWarned = false;
    }

    // выбор из предложенного
    public abstract List<Book> processAvailableBooks(List<Book> availableBooks);

    public abstract GuestOrder makeAnOrder();

    public List<Book> askTheLibrarianAboutOrder(GuestOrder order) {
        if (librarian.libraryIsOpen()) {
            return librarian.lookForAvailableBooks(order);
        } else {
            return null;
        }
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }

    public boolean isWarned() {
        return isWarned;
    }

    public void getWarned() {
        isWarned = true;
    }

    public void returnTheBooks(GuestOrder order) throws IllegalAccessException {
        if (order.getOwner() == this && librarian.libraryIsOpen()) {
            takenBooks.removeAll(order.getTakenBooks());
            librarian.receiveReturnedBooks(order);
        } else {
            throw new IllegalAccessException();
        }
    }
}
