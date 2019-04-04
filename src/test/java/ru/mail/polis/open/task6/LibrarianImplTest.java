package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LibrarianImplTest {

    private static LibrarianImpl librarian;
    private static Book book;
    private static Book notExistBook;
    private static VisitorImpl visitor;

    @BeforeAll
    static void createInstanceAndStartWorking() {
        librarian = new LibrarianImpl(
            "Ustinov",
            "Artem",
            "Germanovich",
            'M',
            18,
            15000
        );

        Library.startWorking();

        book = new Book(
            "Атлант расправил плечи",
            "Рэнд Айн",
            "Проза",
            1394,
            1,
            1,
            2
        );

        visitor = new VisitorImpl(
            "Bogdanova",
            "Irina",
            "Genadievna",
            'F',
            23,
            30000
        );
        notExistBook = new Book(
            "dfbsbzdbb",
            "arbdabadba",
            "abgaagrg",
            1214,
            2,
            2,
            2
        );
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
    void testThrowException() {
        Library
            .getBlackListOfVisitors()
            .add(visitor);

        assertThrows(
            PresenceOfTheBlackListException.class,
            () -> librarian.giveBook(34534525, visitor)
        );
        assertThrows(
            PresenceOfTheBlackListException.class,
            () -> librarian.giveBook("bsgsggg", "ggsgsrgg", visitor)
        );

        assertThrows(
            NullPointerException.class,
            () -> librarian.putBook(null, visitor)
        );
        assertThrows(
            NoSuchBookException.class,
            () -> librarian.searchSuchBooks(notExistBook.getId())
        );
        assertThrows(
            NoSuchBookException.class,
            () -> librarian.searchSuchBooks(
                notExistBook.getName(),
                notExistBook.getAuthor()
            )
        );
    }

    @Test
    void testWorkingMethods() {
        //проверка поиска книги по её ID
        assertEquals(
            book,
            librarian.searchSuchBooks(book.getId())
        );

        librarian.putBook(book, visitor);

        //проверка наличия книги в библиотеке, после того, как библиотекарь положил книгу на место
        assertEquals(
            book,
            Library
                .getLibraryBookcase()
                .get(book.getBookcaseNumber())
                .getShelfInBookcase()
                .get(book.getShelfNumber())
                .getBookShelf()
                .put(
                    book.getShelfSpace(),
                    book
                )
        );

        //проверка поиска книги по её имени и автору
        assertEquals(
            book,
            librarian.searchSuchBooks(
                book.getName(),
                book.getAuthor()
            )
        );

        librarian.updateInfoAfterTakenBook(book, visitor);
        librarian.putBook(book, visitor);

        //проверка наличия книги в библиотеке, после того, как библиотекарь положил книгу на место
        assertEquals(
            book,
            Library
                .getLibraryBookcase()
                .get(book.getBookcaseNumber())
                .getShelfInBookcase()
                .get(book.getShelfNumber())
                .getBookShelf()
                .put(
                    book.getShelfSpace(),
                    book
                )
        );

        Library.getBlackListOfVisitors().clear();

        //проверка выдачи книги по её ID
        assertEquals(
            book,
            librarian.giveBook(
                book.getId(),
                visitor
            )
        );
        librarian.putBook(book, visitor);

        //проверка выдачи книги по её имени и автору
        assertEquals(
            book,
            librarian.giveBook(
                book.getName(),
                book.getAuthor(),
                visitor
            )
        );

        librarian.putBook(book, visitor);

        Book bookWithBusyPlace = new Book(
            "thsthshs",
            "gsgsrgrgsg",
            "rggrgrgzrg",
            234,
            1,
            1,
            1
        );

        librarian.searchPlaceForBook(bookWithBusyPlace);

        //проверка изменения положения книги в библиотеке в случае занятости исходного места
        assertEquals(
            1,
            bookWithBusyPlace.getBookcaseNumber()
        );
        assertEquals(
            2,
            bookWithBusyPlace.getShelfNumber()
        );
        assertEquals(
            3,
            bookWithBusyPlace.getShelfSpace()
        );
    }
}
