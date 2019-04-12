package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private static final int MAX_STORAGE_TIME = 30;
    private Map<Customer, ArrayList<Book>> customers = new HashMap<>();
    private Manager manager;
    private Librarian librarian;
    private Shelf shelf = new Shelf();
    private boolean isOpen = true;


    public Library(Manager manager, Librarian librarian) {
        this.manager = manager;
        this.librarian = librarian;
    }

    public boolean isOpen() {
        return isOpen;
    }

    Map<Customer, ArrayList<Book>> getCustomers() {
        return customers;
    }

    Shelf getShelf() {
        return shelf;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    void open() {
        isOpen = true;
        remind();
    }

    void close() {
        isOpen = false;
    }

    public List<Book> getAvailableBooks() {
        return shelf.availableBooks();
    }

    private void remind() {
        for (Map.Entry<Customer, ArrayList<Book>> customer : customers.entrySet()) {
            for (Book book : customer.getValue()) {
                if (book.getCounter() >= MAX_STORAGE_TIME) {
                    customer.getKey().phone(book);
                }
            }
        }
    }

    public List<TakenBookState> takenBooksInfo() {
        return shelf.takenBooksInfo();
    }
}
