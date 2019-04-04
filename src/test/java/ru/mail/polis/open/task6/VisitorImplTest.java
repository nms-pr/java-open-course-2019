package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VisitorImplTest {

    private static VisitorImpl visitor;
    private static Book book;
    private static String[] names;
    private static String[] authors;
    private static List<Book> takenBooks;
    private static int quantityBooksInLibrary;

    @BeforeAll
    static void createInstanceAndStartWorking() {
        visitor = new VisitorImpl(
            "Bogdanova",
            "Irina",
            "Genadievna",
            'F',
            23,
            30000
        );

        book = new Book(
            "Атлант расправил плечи",
            "Рэнд Айн",
            "Проза",
            1394,
            1,
            1,
            2
        );

        names = new String[]{"Призрак Канта", "Преступление и наказание"};
        authors = new String[]{"Устинова Татьяна Витальевна", "Достоевский Федор Михайлович"};
        takenBooks = new ArrayList<>();
        Library.startWorking();
        quantityBooksInLibrary = Library.showAvailableBooks().size();
    }

    @AfterAll
    static void endWorkingLibrary() {
        Library.endWorking();
        Library.setIsFirstDayWorking(true);
        Library.getVisitors().clear();
        Library.getBlackListOfVisitors().clear();
        Library.getBusyBooks().clear();
        Library.getVisitorsAtLibrary().clear();
        Library.getLibraryBookcase().clear();
        Library.showAvailableBooks().clear();
    }

    @Test
    void testWorkingEquals() {
        VisitorImpl visitor2 = new VisitorImpl(
            "Bogdanova",
            "Irina",
            "Genadievna",
            'F',
            23,
            30000
        );
        visitor.getTakenBooks().clear();
        assertEquals(visitor, visitor2);
    }

    @Test
    void testWorkingTakeBookAndGiveAway() {
        //проверка на получение книги по ID
        visitor.takeBook(book.getId());
        takenBooks.add(book);
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );

        //проверка на возврат 1 книги
        visitor.giveAway(book);
        takenBooks.remove(book);
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );

        //проверка на получение книги по имени и автору
        visitor.takeBook(book.getName(), book.getAuthor());
        takenBooks.add(book);
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );

        //проверка на получение книг по имени и автору
        visitor.takeBook(names, authors);
        takenBooks.add(
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
        takenBooks.add(
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
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );

        //проверка на возврат списка книг
        visitor.giveAway(takenBooks);
        takenBooks.clear();
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );

        //проверка на получение книг по нескольким ID
        takenBooks.add(
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
        takenBooks.add(
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
        long[] ids = new long[takenBooks.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = takenBooks.get(i).getId();
        }
        visitor.takeBook(ids);
        assertEquals(
            visitor.getTakenBooks(),
            takenBooks
        );
        assertEquals(
            quantityBooksInLibrary - takenBooks.size(),
            Library.showAvailableBooks().size()
        );
    }

    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class,
            () -> visitor.takeBook(new String[2], new String[3]));

        assertThrows(IllegalArgumentException.class,
            () -> visitor.takeBook(new String[0], new String[0]));

        assertThrows(IllegalArgumentException.class,
            () -> visitor.takeBook(new long[0]));
    }
}
