package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.library.Book;
import ru.mail.polis.open.task6.library.Library;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Book> myBooks = new ArrayList<>();
    private Book book;
    private String name;
    private Library library;
    private String surname;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void linkWithLibrary(Library library) {
        this.library = library;
    }

    public List<Book> getMyBooks() {
        return myBooks;
    }

    public Book get(String bookName) {
        if (library.isOpen()) {
            book = library.getLibrarian().get(bookName, this);
            myBooks.add(book);
            return book;
        }
        throw new IllegalArgumentException("The library is closed");
    }

    public void put(String bookname) {
        for (Book e : myBooks) {
            if (bookname == e.getName()) {
                myBooks.remove(e);
                library.getLibrarian().put(e);
            }
        }
    }
}
