package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bookcase {
    private static final int SHELF_QUANTITY = 4;

    private final int bookcaseNumber;
    private Map<Integer, Shelf> shelfInBookcase;

    Bookcase(int bookcaseNumber) {
        this.bookcaseNumber = bookcaseNumber;
        this.shelfInBookcase = new HashMap<>();
        for (int i = 1; i <= SHELF_QUANTITY; i++) {
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
            && SHELF_QUANTITY == bookcase.getShelfQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookcaseNumber, SHELF_QUANTITY);
    }

    int getBookcaseNumber() {
        return bookcaseNumber;
    }

    Map<Integer, Shelf> getShelfInBookcase() {
        return shelfInBookcase;
    }

    private int getShelfQuantity() {
        return SHELF_QUANTITY;
    }
}
