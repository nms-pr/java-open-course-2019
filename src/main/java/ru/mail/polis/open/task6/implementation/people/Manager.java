package ru.mail.polis.open.task6.implementation.people;

import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.Category;
import ru.mail.polis.open.task6.interfaces.LibraryForManager;

import java.util.NoSuchElementException;

/**
 * Person, who can open and close library, add and remove books
 * Works as wrapper around Person class
 */
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

    /**
     * Adds book to library
     */
    public void addBook(Book book) {
        library.getBookStorage().addBook(book);
    }

    /**
     * Adds book to library
     */
    public void addBook(String name, String author, Category category) {
        addBook(new Book(name, author, category));
    }

    /**
     * Removes book from library
     * Throws an exception, if book was not found
     */
    public void removeBook(Book book) {
        if (!removeBookIfPresent(book)) {
            throw new NoSuchElementException("No such book in library: " + book);
        }
    }

    /**
     * Removes book from library
     * Throws an exception if book was not found
     */
    public void removeBook(String name, String author, Category category) {
        removeBook(new Book(name, author, category));
    }

    /**
     * Removes book from library
     *
     * @return
     *      true - if removal was successful
     *      false - if book was not found
     */
    public boolean removeBookIfPresent(Book book) {
        try {
            return library.getBookStorage().removeBook(book);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Removes book from library
     *
     * @return
     *      true - if removal was successful
     *      false - if book was not found
     */
    public boolean removeBookIfPresent(String name, String author, Category category) {
        return removeBookIfPresent(new Book(name, author, category));
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

    @Override
    public String toString() {
        return "Manager " + self.toString();
    }
}
