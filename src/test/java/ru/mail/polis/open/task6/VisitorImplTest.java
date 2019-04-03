package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VisitorImplTest {

    private static VisitorImpl visitor;
    private static Book book;
    private static String[] names;
    private static String[] authors;
    private static long[] IDs;
    private static List<Book> takenBooks;
    private static int quantityBooksInLibrary;

    @BeforeAll
    static void createInstance() {
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
        assertEquals(visitor, visitor2);
    }

    @Test
    void testWorkingTakeBookID() {
        visitor.takeBook(book.getID());
        takenBooks.add(book);
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
    void testWorkingGiveAwayOneBook() {
        visitor.giveAway(takenBooks);
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
    void testWorkingTakeBookNameAndAuthor() {
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
    }

    @Test
    void testWorkingTakeBookArrayNamesAndAuthors() {
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
    }

    @Test
    void testWorkingGiveAwaySomeBook() {
        visitor.giveAway(takenBooks);
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
    void testWorkingTakeBookArrayID() {
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
        IDs = new long[takenBooks.size()];
        for (int i = 0; i < IDs.length; i++) {
            IDs[i] = takenBooks.get(i).getID();
        }
        visitor.takeBook(IDs);
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
