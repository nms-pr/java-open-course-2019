package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForManager;

import java.util.NoSuchElementException;

public class Manager extends Person {

    private final Person himself;
    private final LibraryForManager library;

    public Manager(Person himself, LibraryForManager library) {
        super(himself.getFirstName(), himself.getLastName());
        this.himself = himself;
        this.library = library;
    }

    public void addBook(Book book) {

    }

    public void addBook(String name, String author, Category category) {
        addBook(new Book(name, author, category));
    }

    public void deleteBook(Book book) {
        if(!deleteBookIfPresent(book)) {
            throw new NoSuchElementException("No such book in library: " + book);
        }
    }

    public void deleteBook(String name, String author, Category category) {
        deleteBook(new Book(name, author, category));
    }

    public boolean deleteBookIfPresent(Book book) {

    }

    public boolean deleteBookIfPresent(String name, String author, Category category) {
        deleteBook(new Book(name, author, category));
    }
}
