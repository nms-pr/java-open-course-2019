package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Library {
    static ManagerImpl manager = new ManagerImpl(
        "Petrov",
        "Artem",
        "Igorevich",
        'M',
        18,
        50000
    );
    static LibrarianImpl librarian = new LibrarianImpl(
        "Ustinov",
        "Artem",
        "Andreevich",
        'M',
        18,
        15000
    );
    static List<VisitorImpl> visitors = new ArrayList<>();

    private static List<Book> busyBooks = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static final int QUANTITY_WARDROBE = 10;
    private static Map<Integer, Bookcase> libraryBookcase = new HashMap<>();
    private static boolean isOpened = false;
    private static boolean isFirstDayWorking = true;

    private Library() {}

    public static boolean isOpened() {
        return isOpened;
    }

    public static void setOpened(boolean opened) {
        isOpened = opened;
    }

    public static Map<Integer, Bookcase> getLibraryBookcase() {
        return libraryBookcase;
    }

    public static List<Book> getBusyBooks() {
        return busyBooks;
    }

    static List<Book> showAvailableBooks() {
        return books;
    }

    static void startWorking() {
        if (isFirstDayWorking) {
            for (int i = 0; i < QUANTITY_WARDROBE; i++) {
                libraryBookcase.put(i, new Bookcase(i));
            }

            for (Bookcase bookcase : libraryBookcase.values()) {
                for (Shelf shelf : bookcase.getShelfInBookcase().values()) {
                    books.addAll(shelf.getBookShelf().values());
                }
            }
            isFirstDayWorking = false;
        }

        visitors.add(
            new VisitorImpl(
                "Fedorov",
                "Sergey",
                "Alekseevich",
                'M',
                19,
                25000
            )
        );
        visitors.add(
            new VisitorImpl(
                "Fedorov",
                "Dmitriy",
                "Mickhailovich",
                'M',
                18,
                23000
            )
        );
        visitors.add(
            new VisitorImpl(
                "Bogdanova",
                "Irina",
                "Genadievna",
                'F',
                23,
                30000
            )
        );

        isOpened = true;
    }

    static void endWorking() {
        visitors.clear();
        isOpened = false;
    }

    static boolean isBusyPlace (
        int bookcaseNumber,
        int shelfNumber,
        int placeNumber
    ) {
        return
            libraryBookcase
            .get(bookcaseNumber)
            .getShelfInBookcase()
            .get(shelfNumber)
            .getBookShelf()
            .containsKey(placeNumber);
    }
}
