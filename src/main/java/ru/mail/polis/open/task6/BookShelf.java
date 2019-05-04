package ru.mail.polis.open.task6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookShelf {

    static int booksCount;
    private Map<String, List<Book>> shelf;
    private static BookShelf bookShelf = new BookShelf();

    private BookShelf() {
        shelf = new HashMap<>();
    }

    public static void setBooksCount(int booksCount) {
        BookShelf.booksCount = booksCount;
    }

    public static BookShelf getShelf() {
        return bookShelf;
    }

    public Map<String, List<Book>> getBookShelf() {
        return shelf;
    }

    public void showShelf() {
        for (Map.Entry<String, List<Book>> entry : shelf.entrySet()) {
            System.out.println(entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println(book);
            }
            System.out.println();
        }
    }
}
