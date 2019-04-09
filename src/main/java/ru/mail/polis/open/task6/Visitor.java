package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

/**
 * Есть посетители (берёт и отдаёт книги)
 */
public class Visitor {

    private String name;

    private List<Book> booksOnHand;

    public Visitor(String name) {
        this.name = name;
        booksOnHand = new ArrayList<>();
    }

    boolean takesBook(Book... books) {
        for (int i = 0; i < books.length; i++) {
            booksOnHand.add(books[i]);
        }
        return true;
    }

    boolean givesBooks(Book books) {
        if (booksOnHand.contains(books)) {
            booksOnHand.remove(books);
        } else {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "[" + booksOnHand.toString() + "]";
    }

    protected List<Book> getBooksOnHand() {
        return booksOnHand;
    }
}
