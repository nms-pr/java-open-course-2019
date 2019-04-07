package ru.mail.polis.open.task6.implementation;

import ru.mail.polis.open.task6.implementation.book.Book;
import ru.mail.polis.open.task6.implementation.book.BookInfo;
import ru.mail.polis.open.task6.interfaces.BookProvider;
import ru.mail.polis.open.task6.interfaces.BookStorage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


/**
 * Storage for books
 * Stores pairs (Book, BookInfo)
 */
public class BookShelf implements BookProvider, BookStorage {

    private Map<Book, BookInfo> books;

    // All books have id, this variable ensures they are all unique
    private int notUsedId = 0;

    public BookShelf() {

        books = new HashMap<>();
    }

    @Override
    public Set<Book> getAllBooks() {

        return books.keySet();
    }

    /**
     * Adds book to storage
     * Member of BookStorage interface
     */
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

    /**
     * Removes book from storage
     * Member of BookStorage interface
     */
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

    /**
     * Removes all instances of book from storage
     * Member of BookStorage interface
     */
    @Override
    public boolean removeAllBookInstances(Book book) {

        if (!books.containsKey(book)) {
            return false;
        }

        books.remove(book);
        return true;
    }

    /**
     * @param book - book to lend
     * @return information about book
     */
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

    /**
     * @param book - book to retrieve
     * @return information about book
     */
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

    /**
     * Finds lowest place at shelf
     *
     * For example (books are represented with '*', empty places with '-')
     * Assuming books are standing from left to right, most right place has index 0
     *
     * ---------- - returns 0
     * **-------- - returns 2
     * ******---- - returns 6
     * -*****---- - returns 0
     * ***--***** - returns 3
     *
     * @return lowest place
     */
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
