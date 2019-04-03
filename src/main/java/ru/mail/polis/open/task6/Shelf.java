package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Shelf {
    private final int shelfNumber;
    private final int bookcaseNumber;
    private Map<Integer, Book> bookShelf;
    private final int capacity;

    public Shelf(
        int shelfNumber,
        int bookcaseNumber) {

        this.shelfNumber = shelfNumber;
        this.bookcaseNumber = bookcaseNumber;
        capacity = 4;
        this.bookShelf = new HashMap<>();

        for (int i = 0; i < capacity; i++) {
            bookShelf.put(i + 1, null);
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

    public Map<Integer, Book> getBookShelf() {
        return bookShelf;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBookcaseNumber() {
        return bookcaseNumber;
    }
}
