package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;
import ru.mail.polis.open.task6.People;

import java.util.List;

public class Librarian {
    private String name;
    private Library library;

    public Librarian(String name) {
        this.name = name;
    }

    public void linkWithLibrary(Library library) {
        this.library = library;
    }

    public void put(Book book) {
        library.getShelf().put(book);
    }

    public Book get(String bookName, Customer customer) {
        return library.getShelf().take(bookName, customer);
    }
}
