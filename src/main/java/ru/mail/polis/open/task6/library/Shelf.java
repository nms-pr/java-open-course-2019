package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    List<Book> shelf = new ArrayList<>();
    List<Book> availableBooks = new ArrayList<>();
    private Book book;

    void add(Book book) {
        shelf.add(book);
        availableBooks.add(book);
    }

    void remove(Book book) {
        for (Book e : shelf) {
            if (book.equals(e)) {
                if (e.getowner() == null) {
                    shelf.remove(e);
                    availableBooks.remove(e);
                } else {
                    throw new IllegalArgumentException("can't remove this book, it's not in library now");
                }
            }
        }
    }

    void put(Book book) {
        for (Book e : shelf) {
            if (e.equals(book)) {
                e.setOuner(null);
                availableBooks.add(e);
            }
        }
    }

    Book take(String bookName, Customer customer) {
        for (Book e : shelf) {
            if (e.getName() == bookName) {
                if (e.getowner() != null) {
                    throw new IllegalArgumentException("this book is already taken");
                }
                e.setOuner(customer);
                availableBooks.remove(e);
                return e;
            }
        }
        throw new IllegalArgumentException("no suck book");
    }


    List<Book> availableBooks() {
        return availableBooks;
    }

    //список нужных книг
    List giveBooks() {
        return null;
    }

    //напоминалка о возврате книг
    String reminde() {
        return null;
    }
}
