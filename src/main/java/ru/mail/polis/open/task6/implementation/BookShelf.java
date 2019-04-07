package ru.mail.polis.open.task6.implementation;

import ru.mail.polis.open.task6.implementation.Book.Book;
import ru.mail.polis.open.task6.implementation.Book.BookInfo;
import ru.mail.polis.open.task6.interfaces.BookProvider;
import ru.mail.polis.open.task6.interfaces.BookStorage;

import java.util.*;

public class BookShelf implements BookProvider, BookStorage {

    private Map<Book, BookInfo> books;

    private int notUsedId = 0;

    public BookShelf() {

        books = new HashMap<>();
    }

    @Override
    public Set<Book> getAllBooks() {

        return books.keySet();
    }

    @Override
    public void addBook(Book book) {

        if (books.containsKey(book)) {
            books.get(book).onNewInstanceAdded(findLowestShelfPlace());
            return;
        }

        Set<Integer> shelfPlaces = new HashSet<>();
        shelfPlaces.add(findLowestShelfPlace());
        books.put(book, new BookInfo(notUsedId++, 1, shelfPlaces));
    }

    @Override
    public boolean removeBook(Book book) {

        if (!books.containsKey(book)) {
            return false;
        }

        BookInfo bookInfo = books.get(book);
        bookInfo.onInstanceRemoved(bookInfo.getShelfPlaces().iterator().next());

        if (bookInfo.getTotal() == 0) {
            books.remove(book);
        }
        return true;

    }

    @Override
    public boolean removeAllBookInstances(Book book) {

        if (!books.containsKey(book)) {
            return false;
        }

        books.remove(book);
        return true;
    }

    @Override
    public BookInfo lendBook(Book book) {

        if (!books.containsKey(book)) {
            throw new NoSuchElementException("No such book in bookshelf");
        }

        BookInfo bookInfo = books.get(book);

        if (bookInfo.getInStock() == 0) {
            throw new NoSuchElementException("No such book in stock");
        }

        bookInfo.onInstanceLent(bookInfo.getShelfPlaces().iterator().next());

        return bookInfo;
    }

    @Override
    public BookInfo retrieveBook(Book book) {

        if (!books.containsKey(book)) {
            throw new NoSuchElementException("No such book in bookshelf");
        }

        BookInfo bookInfo = books.get(book);

        bookInfo.onInstanceReturned(findLowestShelfPlace());

        return bookInfo;
    }

    @Override
    public BookInfo getBookInfo(Book book) {
        return books.get(book);
    }

    private int findLowestShelfPlace() {

        for (int lowest = 0; ; lowest++) {
            boolean found = false;
            for (Map.Entry<Book, BookInfo> entry : books.entrySet()) {
                for (int shelfPlace : entry.getValue().getShelfPlaces()) {
                    if (shelfPlace == lowest) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            if (!found) {
                return lowest;
            }
        }
    }
}
