package ru.mail.polis.open.task6;

import java.util.List;
import java.util.Objects;

public class ManagerImpl implements Manager {

    private Library library;
    private List<Book> books;

    public ManagerImpl(Library library) {
        this.library = library;
        this.books = library.getBooksAll();
    }

    @Override
    public boolean bringBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            library.getBooksAvailable().add(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            library.getBooksAvailable().remove(book);
            return true;
        }
        return false;
    }

    @Override
    public void openInstitution() {
        library.open();
    }

    @Override
    public void closeInstitution() {
        library.close();
    }
}