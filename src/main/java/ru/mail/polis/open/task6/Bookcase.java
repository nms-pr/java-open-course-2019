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
        for (int i = 0; i < shelfQuantity; i++) {
            shelfInBookcase.put(i + 1, new Shelf(i + 1, bookcaseNumber));
        }
//        Shelf shelf1 = new Shelf(1, bookcaseNumber);
//        shelfInBookcase.put(shelf1.getShelfNumber(), shelf1);
//        Shelf shelf2 = new Shelf(2, bookcaseNumber);
//        shelfInBookcase.put(shelf2.getShelfNumber(), shelf2);
//        Shelf shelf3 = new Shelf(3, bookcaseNumber);
//        shelfInBookcase.put(shelf3.getShelfNumber(), shelf3);
//        Shelf shelf4 = new Shelf(4, bookcaseNumber);
//        shelfInBookcase.put(shelf4.getShelfNumber(), shelf4);
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
}
