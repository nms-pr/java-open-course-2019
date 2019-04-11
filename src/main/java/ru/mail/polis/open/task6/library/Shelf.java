package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


class Shelf {
    private Map<String, List<Book>> shelf = new HashMap<>();
    private List<Book> availableBooks = new ArrayList<>();
    private Book localBook;
    private List<String> sections = new ArrayList<>();


    void add(Book book) {
        if (!shelf.containsKey(book.getSection())) {
            shelf.put(book.getSection(), new ArrayList<>());
            sections.add(book.getSection());
        }
        shelf.get(book.getSection()).add(book);
        availableBooks.add(book);
    }

    void remove(Book book) {
        if (!shelf.containsKey(book.getSection())) {
            throw new IllegalArgumentException("no such section");
        }
        for (Book e : shelf.get(book.getSection())) {
            if (book.equals(e)) {
                if (e.getowner() == null) {
                    localBook = e;
                    availableBooks.remove(e);
                } else {
                    throw new IllegalArgumentException("can't remove this book, it's not in library now");
                }
            }
        }
        if (localBook == null) {
            throw new IllegalArgumentException("no such book");
        }
        shelf.get(book.getSection()).remove(localBook);
        if (shelf.get(book.getSection()).isEmpty()) {
            shelf.remove(book.getSection());
            sections.remove(book.getSection());
        }
        localBook = null;
    }

    void returnBook(Book book) {
        if (!shelf.containsKey(book.getSection())) {
            throw new IllegalArgumentException("no such section");
        }
        for (Book e : shelf.get(book.getSection())) {
            if (e.equals(book)) {
                e.setOwner(null);
                e.setDate(null);
                availableBooks.add(e);
                return;
            }
        }
        throw new IllegalArgumentException("it'n not our book");
    }

    Book takeBook(String section, String bookName, Customer customer) {
        if (!sections.contains(section)) {
            throw new IllegalArgumentException("no such section");
        }
        for (Book e : shelf.get(section)) {
            if (e.getName().equals(bookName)) {
                if (e.getowner() != null) {
                    throw new IllegalArgumentException("this book is already taken");
                }
                e.setOwner(customer);
                e.setDate(new Date());
                availableBooks.remove(e);
                return e;
            }
        }
        throw new IllegalArgumentException("no suck book");
    }


    List<Book> availableBooks() {
        return availableBooks;
    }

}
