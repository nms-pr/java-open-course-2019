package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.library.Book;
import ru.mail.polis.open.task6.library.Library;


public class Customer {
    private String name;
    private Library library;
    private String surname;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void linkWithLibrary(Library library) {
        this.library = library;
    }


    public Book takeBook(String section, String bookName) {
        if (library.isOpen()) {
            return library.getLibrarian().get(section, bookName, this);

        }
        throw new IllegalArgumentException("The library is closed");
    }

    public void returnBook(Book book) {
        if (library.isOpen()) {
            library.getLibrarian().put(book, this);
            return;
        }
        throw new IllegalArgumentException("The library is closed");
    }

    public void phone(Book book) {
        returnBook(book);
        //or ignore
    }
}
