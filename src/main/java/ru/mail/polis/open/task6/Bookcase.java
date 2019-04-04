package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bookcase {
    private final int bookcaseNumber;
    private Map<Integer, Shelf> shelfInBookcase;
    private final int shelfQuantity;

    public Bookcase(int bookcaseNumber) {
        shelfQuantity = 4;
        this.bookcaseNumber = bookcaseNumber;
        this.shelfInBookcase = new HashMap<>();
        for (int i = 1; i <= shelfQuantity; i++) {
            shelfInBookcase.put(i, new Shelf(i, bookcaseNumber));
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
        Bookcase bookcase = (Bookcase) o;
        return bookcaseNumber == bookcase.getBookcaseNumber()
            && shelfQuantity == bookcase.getShelfQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookcaseNumber, shelfQuantity);
    }

    public int getBookcaseNumber() {
        return bookcaseNumber;
    }

    public Map<Integer, Shelf> getShelfInBookcase() {
        return shelfInBookcase;
    }

    public int getShelfQuantity() {
        return shelfQuantity;
    }
}
