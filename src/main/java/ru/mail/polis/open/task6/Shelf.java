package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private static final byte PLACES_IN_ONE_SHELF = 50;
    public int shelf;
    public List<Book> booksOnShelf;

    public Shelf(int shelf) {
        this.shelf = shelf - 1;
        booksOnShelf = new ArrayList<>();
        for (int i = 0; i < PLACES_IN_ONE_SHELF; i++) {
            booksOnShelf.add(null);
        }
    }

    public int getShelf() {
        return (shelf + 1);
    }

    public byte getQuality() {
        return PLACES_IN_ONE_SHELF;
    }

    public List<Book> getBooksOnShelf() {
        return booksOnShelf;
    }


}
