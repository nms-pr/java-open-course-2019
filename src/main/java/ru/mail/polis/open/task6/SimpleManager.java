package ru.mail.polis.open.task6;

import java.util.Collection;

public class SimpleManager implements Manager {
    ManagableLibrary library;

    public SimpleManager(ManagableLibrary library) {
        this.library = library;
    }

    @Override
    public void addBook(Book book) {
        try {
            library.addToLibrary(book);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(Book book) {
        try {
            library.remove(book);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void openLibrary() {
        library.open();
    }

    @Override
    public void closeLibrary() {
        library.close();
    }

    @Override
    public void addBooks(Collection<Book> books) {
        try {
            library.addToLibrary(books);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }

}
