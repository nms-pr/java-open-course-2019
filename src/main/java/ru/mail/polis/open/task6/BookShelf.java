package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private final int SHELF_QUANTITY = 20;
    public int shelf;
    private List<Book> booksOnShelf;

    public BookShelf(int shelf) {
        this.shelf = shelf - 1;
        booksOnShelf = new ArrayList<>(SHELF_QUANTITY);
        for (int i = 0; i < SHELF_QUANTITY; i++) {
            booksOnShelf.add(null);
        }
    }

    public int getShelfQuantity() {
        return SHELF_QUANTITY;
    }

    public List<Book> getBooksFromShelf() {
        return booksOnShelf;
    }

    public int getBookShelf() { return shelf - 1 ; }
}
