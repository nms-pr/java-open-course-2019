package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private static final byte PLACES_ON_THE_SHELF = 50;
    public int numberBookShelf;
    public List<Book> booksOnShelf;

    public BookShelf(int numberBookShelf) {
        this.numberBookShelf = numberBookShelf - 1;
        booksOnShelf = new ArrayList<>();
        for (int i = 0; i < PLACES_ON_THE_SHELF; i++) {
            booksOnShelf.add(null);
        }
    }

    public static byte getPlacesOnTheShelf() {
        return PLACES_ON_THE_SHELF;
    }

    public int getNumberBookShelf() {
        return numberBookShelf + 1;
    }

    public List<Book> getBooksOnShelf() {
        return booksOnShelf;
    }
}


