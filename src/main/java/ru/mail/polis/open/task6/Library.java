package ru.mail.polis.open.task6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Library {
    private static final int WARDROBE_QUANTITY = 4;

    static ManagerImpl manager = new ManagerImpl(
        "Petrov",
        "Artem",
        "Igorevich",
        'M',
        18,
        1000000
    );
    static LibrarianImpl librarian = new LibrarianImpl(
        "Ustinov",
        "Artem",
        "Germanovich",
        'M',
        18,
        15000
    );

    private static List<VisitorImpl> visitorsAtLibrary = new ArrayList<>();
    private static List<VisitorImpl> visitors = new ArrayList<>();
    private static List<VisitorImpl> blackListOfVisitors = new ArrayList<>();
    private static List<Book> busyBooks = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static Map<Integer, Bookcase> libraryBookcase = new HashMap<>();
    private static boolean isOpened = false;
    private static boolean isFirstDayWorking = true;

    private Library() {}

    static void startWorking() {
        if (isFirstDayWorking) {
            for (int i = 1; i <= WARDROBE_QUANTITY; i++) {
                libraryBookcase.put(i, new Bookcase(i));
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

            initBasicBooks();
            isFirstDayWorking = false;
        } else {
            for (Book book : busyBooks) {
                librarian.remindToVisitor(book.getUser());
            }
        }

        //добавление постоянных посетитилей
        for (int i = 0; i < 3; i++) {
            visitorsAtLibrary.add(visitors.get(i));
        }
        isOpened = true;
    }

    static void endWorking() {
        visitorsAtLibrary.clear();
        isOpened = false;
    }

    static boolean isBusyPlace(
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

    private static void initBasicBooks() {
        books.add(
            new Book(
                "Мятная сказка",
                "Полярный Александр",
                "Проза",
                208,
                1,
                1,
                1
            )
        );
        books.add(
            new Book(
                "Смертельная белизна",
                "Гэлбрейт Роберт",
                "Детективы, боевики, триллеры",
                672,
                2,
                1,
                1
            )
        );
        books.add(
            new Book(
                "Атлант расправил плечи",
                "Рэнд Айн",
                "Проза",
                1394,
                1,
                1,
                2
            )
        );
        books.add(
            new Book(
                "Клатбище домашних жывотных",
                "Кинг Стивен",
                "Фантастика, мистика",
                480,
                3,
                1,
                1
            )
        );
        books.add(
            new Book(
                "Брисбен",
                "Водолазкин Евгений Германович",
                "Проза",
                416,
                1,
                1,
                3
            )
        );
        books.add(
            new Book(
                "Не такая, как все",
                "Леви Марк",
                "Проза",
                368,
                1,
                1,
                4
            )
        );
        books.add(
            new Book(
                "Зов Кукушки",
                "Гэлбрейт Роберт",
                "Детективы, боевики, триллеры",
                512,
                2,
                1,
                2
            )
        );
        books.add(
            new Book(
                "Уникальный экземпляр. Истории о том о сём",
                "Хэнкс Том Джеффри",
                "Проза",
                416,
                1,
                2,
                1
            )
        );
        books.add(
            new Book(
                "Призрак Канта",
                "Устинова Татьяна Витальевна",
                "Детективы, боевики, триллеры",
                352,
                2,
                1,
                3
            )
        );
        books.add(
            new Book(
                "Преступление и наказание",
                "Достоевский Федор Михайлович",
                "Проза",
                592,
                1,
                2,
                2
            )
        );

        for (Book book : books) {
            libraryBookcase
                .get(book.getBookcaseNumber())
                .getShelfInBookcase()
                .get(book.getShelfNumber())
                .getBookShelf()
                .put(
                    book.getShelfSpace(),
                    book
                );
        }
    }

    static void onVisit(VisitorImpl visitor) {
        if (!isOpened) {
            throw new ClosedLibraryException("Now library is closed. Come back later!");
        }
        for (Book book : busyBooks) {
            if (visitor.equals(book.getUser())) {
                visitor.getTakenBooks().add(book);
            }
        }
        visitorsAtLibrary.add(visitor);
        if (!visitors.contains(visitor)) {
            visitors.add(visitor);
        }
    }

    static List<Book> getBooks() {
        return books;
    }

    static void leaveVisitor(VisitorImpl visitor) {
        visitorsAtLibrary.remove(visitor);
    }
    
    static boolean isOpened() {
        return isOpened;
    }

    static void setOpened(boolean opened) {
        isOpened = opened;
    }

    static Map<Integer, Bookcase> getLibraryBookcase() {
        return libraryBookcase;
    }

    static List<Book> getBusyBooks() {
        return busyBooks;
    }

    static List<VisitorImpl> getBlackListOfVisitors() {
        return blackListOfVisitors;
    }

    static void setIsFirstDayWorking(boolean setFirstDayWorking) {
        isFirstDayWorking = setFirstDayWorking;
    }

    static List<VisitorImpl> getVisitorsAtLibrary() {
        return visitorsAtLibrary;
    }

    public static List<VisitorImpl> getVisitors() {
        return visitors;
    }
}
