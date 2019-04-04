package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Shelf {
    private static final int CAPACITY = 4;

    private final int shelfNumber;
    private final int bookcaseNumber;
    private Map<Integer, Book> bookShelf;

    Shelf(
        int shelfNumber,
        int bookcaseNumber) {

        this.shelfNumber = shelfNumber;
        this.bookcaseNumber = bookcaseNumber;
        this.bookShelf = new HashMap<>();

        for (int i = 1; i <= CAPACITY; i++) {
            bookShelf.put(i, null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shelf shelf = (Shelf) o;
        return shelfNumber == shelf.getShelfNumber()
            && CAPACITY == shelf.getCapacity()
            && bookcaseNumber == shelf.getBookcaseNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, CAPACITY, bookcaseNumber);
    }

    Map<Integer, Book> getBookShelf() {
        return bookShelf;
    }

    int getShelfNumber() {
        return shelfNumber;
    }

    int getCapacity() {
        return CAPACITY;
    }

    private int getBookcaseNumber() {
        return bookcaseNumber;
    }
}
