package ru.mail.polis.open.task6.office;

import ru.mail.polis.open.task6.Genres;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;


class LibraryStorage {

    private boolean isOpen;
    private List<Book> bookStorage;
    private int capacity;

    LibraryStorage() {
        this.bookStorage = new ArrayList<>();
        isOpen = false;
        capacity = 64;
    }

    boolean isOpen() {
        return isOpen;
    }

    void setOpen() {
        isOpen = true;
    }

    void setClosed() {
        isOpen = false;
    }

    public int getCapacity() {
        return capacity;
    }

    boolean putBook(Book book) {
        if (capacity > 0 && book != null) {
            bookStorage.add(book);
            capacity--;
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    boolean putBook(List<Book> bookList) {
        if (bookList != null) {
            for (Book currentBook: bookList) {
                if (putBook(currentBook)) {
                    continue;
                }
                return false;
            }
            return true;
        } else {
            throw new IllegalArgumentException();
        }

    }

    boolean removeBook(Book book) {
        if (book != null) {
            bookStorage.remove(book);
            capacity--;
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    boolean removeBook(List<Book> bookList) {
        if (bookList != null) {
            for (Book book: bookList) {
                if (removeBook(book)) {
                    continue;
                }
                return false;
            }
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    List<Book> getBooks(Predicate<Book> predicate) {
        List<Book> suitableBooks = new ArrayList<>();
        for (Book book: bookStorage) {
            if (predicate.test(book)) {
                suitableBooks.add(book);
            }
        }
        return suitableBooks;
    }
}
