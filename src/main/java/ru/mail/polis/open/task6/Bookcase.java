package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;

public class Bookcase {
    private final int bookcaseNumber;
    private Map<Integer, Shelf> shelfInBookcase;
    private final int shelfQuantity;

    public Bookcase(int bookcaseNumber) {
        shelfQuantity = 10;
        this.bookcaseNumber = bookcaseNumber;
        this.shelfInBookcase = new HashMap<>();
        for (int i = 0; i < shelfQuantity; i++) {
            shelfInBookcase.put(i, new Shelf(i));
        }

    }

    public int getBookcaseNumber() {
        return bookcaseNumber;
    }

    public Map<Integer, Shelf> getShelfInBookcase() {
        return shelfInBookcase;
    }
}
