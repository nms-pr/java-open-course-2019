package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForManager;

import java.util.NoSuchElementException;

public class Manager extends Person {

    private final Person self;
    private  LibraryForManager library;

    public Manager(Person self) {
        this.self = self;
    }

    public void assignToLibrary(LibraryForManager library) {
        this.library = library;
    }

    public Person getSelf() {
        return self;
    }

    public void addBook(Book book) {
        library.getBookStorage().addBook(book);
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
        try {
            return library.getBookStorage().removeBook(book);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean deleteBookIfPresent(String name, String author, Category category) {
        return deleteBookIfPresent(new Book(name, author, category));
    }

    public void openLibrary() {
        library.open();
    }

    public void closeLibrary() {
        library.close();
    }

    @Override
    public String getFirstName() {
        return self.getFirstName();
    }

    @Override
    public String getLastName() {
        return self.getLastName();
    }
}
