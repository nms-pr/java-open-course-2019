package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;
import ru.mail.polis.open.task6.People;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private String name;
    private Library library;
    private Book book;

    public Librarian(String name) {
        this.name = name;
    }

    public void linkWithLibrary(Library library) {
        this.library = library;
    }

    public void put(Book book, Customer customer) {
        library.getShelf().returnBook(book);
        library.getCustomers().get(customer).remove(book);
    }

    public Book get(String section, String bookName, Customer customer) {
        book = library.getShelf().takeBook(section, bookName, customer);
        if (!library.getCustomers().containsKey(customer)) {
            library.getCustomers().put(customer, new ArrayList<>());
        }
        library.getCustomers().get(customer).add(book);
        return book;
    }
}
