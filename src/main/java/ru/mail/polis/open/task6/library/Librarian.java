package ru.mail.polis.open.task6.library;

import ru.mail.polis.open.task6.Customer;

import java.util.ArrayList;
import java.util.List;

public class Librarian {
    private String name;
    private Library library;
    private Book book;
    private List<Book> books = new ArrayList<>();

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

    public Book get(Customer customer,String section, String bookName) {
        book = library.getShelf().takeBook(section, bookName, customer);
        if (!library.getCustomers().containsKey(customer)) {
            library.getCustomers().put(customer, new ArrayList<>());
        }
        library.getCustomers().get(customer).add(book);
        return book;
    }

    public List<Book> get(Customer customer, String...request) {
        books.clear();
        for (int i = 1; i < request.length; i++) {
            books.add(get(customer,request[0],request[i]));
        }
        return books;
    }
}
