package ru.mail.polis.open.task6.implementation;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.BookInfo;
import ru.mail.polis.open.task6.interfaces.BookProvider;
import ru.mail.polis.open.task6.interfaces.BookStorage;

import java.util.HashMap;
import java.util.Map;

public class BookShelf implements BookProvider, BookStorage {

    Map<Book, BookInfo> books;

    public BookShelf() {

        books = new HashMap<>();
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public boolean removeBook(Book book) {
        return false;
    }

    @Override
    public boolean removeAllBookInstances(Book book) {
        return false;
    }

    @Override
    public BookInfo lendBook(Book book) {
        return null;
    }

    @Override
    public BookInfo retrieveBook(Book book) {
        return null;
    }

    BookInfo getBookInfo(Book book) {
        return books.get(book);
    }
}
