package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Shelf {
    private final int shelfNumber;
    private final int bookcaseNumber;
    private Map<Integer, Book> bookShelf;
    private final int capacity;

    Shelf(
        int shelfNumber,
        int bookcaseNumber) {

        this.shelfNumber = shelfNumber;
        this.bookcaseNumber = bookcaseNumber;
        capacity = 4;
        this.bookShelf = new HashMap<>();

        for (int i = 1; i <= capacity; i++) {
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
            && capacity == shelf.getCapacity()
            && bookcaseNumber == shelf.getBookcaseNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfNumber, capacity, bookcaseNumber);
    }

    Map<Integer, Book> getBookShelf() {
        return bookShelf;
    }

    int getShelfNumber() {
        return shelfNumber;
    }

    int getCapacity() {
        return capacity;
    }

    private int getBookcaseNumber() {
        return bookcaseNumber;
    }
}
