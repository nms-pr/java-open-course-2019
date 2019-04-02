package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;

public class Shelf {
    private final int shelfNumber;
    private Map<Integer, Book> bookShelf;
    private final int capacity;

    public Shelf(int shelfNumber) {
        this.shelfNumber = shelfNumber;
        capacity = 20;
        this.bookShelf = new HashMap<>();
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
}
