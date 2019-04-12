package ru.mail.polis.open.task6;

import ru.mail.polis.open.task6.library.Book;
import ru.mail.polis.open.task6.library.Library;

import java.util.List;


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
            return library.getLibrarian().get(this,section, bookName);

        }
        throw new IllegalArgumentException("The library is closed");
    }

    public List<Book> takeBook(String ... request){
        if (library.isOpen()) {
            return library.getLibrarian().get(this,request);

        }
        throw new IllegalArgumentException("The library is closed");
    }

    public void returnBook(Book ... book) {
        if (library.isOpen()) {
            for (int i = 0; i <book.length ; i++) {
                library.getLibrarian().put(book[i], this);
            }
            return;
        }
        throw new IllegalArgumentException("The library is closed");
    }


    public void phone(Book book) {
        returnBook(book);
        //or ignore
    }
}
