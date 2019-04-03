package ru.mail.polis.open.task6;

import org.jetbrains.annotations.Contract;

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

    private static List<VisitorImpl> blackListOfVisitors = new ArrayList<>();
    private static List<Book> busyBooks = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static final int QUANTITY_WARDROBE = 4;
    private static Map<Integer, Bookcase> libraryBookcase = new HashMap<>();
    private static boolean isOpened = false;
    private static boolean isFirstDayWorking = true;

    private Library() {}

    static void startWorking() {
        if (isFirstDayWorking) {
            for (int i = 0; i < QUANTITY_WARDROBE; i++) {
                libraryBookcase.put(i, new Bookcase(i));
            }

            initBasicBooks();
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
                "Гэлбрейт Роберт,",
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

    void comeVisitor(VisitorImpl visitor) {
        visitors.add(visitor);
    }

    void leaveVisitor(VisitorImpl visitor) {
        visitors.remove(visitor);
    }
    
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

    public static List<VisitorImpl> getBlackListOfVisitors() {
        return blackListOfVisitors;
    }
}
