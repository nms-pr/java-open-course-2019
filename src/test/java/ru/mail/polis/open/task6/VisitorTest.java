package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VisitorTest {

    private Visitor testVisitor;

    @AfterAll
    static void cleanLibraryStorage() {
        LibraryStorage.getAvailableBooks().clear();
        LibraryStorage.getGivenBooks().clear();
    }

    @BeforeEach
    void refreshStore() {
        Library.open();
        LibraryStorage.getAvailableBooks().clear();
        LibraryStorage.getGivenBooks().clear();
    }

    @BeforeEach
    void fillLibrary() {

        Book firstBook = new Book("Курсы мертвых магов", 10);
        LibraryStorage.addNewBook(firstBook);
        firstBook.setShelfSpace(0);
        Book secondBook = new Book("Тайная зима", 23);
        LibraryStorage.addNewBook(secondBook);
        secondBook.setShelfSpace(0);
        Book thirdBook = new Book("Одноглазая ловушка", 23);
        LibraryStorage.addNewBook(thirdBook);
        thirdBook.setShelfSpace(1);
        Book fourthBook = new Book("Страницы каждой жизни", 10);
        LibraryStorage.addNewBook(fourthBook);
        fourthBook.setShelfSpace(1);
        Book fifthBook = new Book("Море убитой веры", 17);
        LibraryStorage.addNewBook(fifthBook);
        fifthBook.setShelfSpace(0);
        Book notExistingBook = new Book("Талантливый спор", 15);
        notExistingBook.setShelfSpace(0);

        testVisitor = new Visitor("Герман", "Греф");
    }

    @Test
    void getBookByNameTest() {

        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(10).get(0));
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(23).get(0));
        Assertions.assertEquals(0, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
        testVisitor.getBooks(new String[]{"Курсы мертвых магов", "Тайная зима"}, new int[]{10, 23});
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(10).get(0));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(23).get(0));
        Assertions.assertEquals(2, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(2, LibraryStorage.getGivenBooks().size());

    }

    @Test
    void getBookByIdTest() {

        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(17).get(0));
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(23).get(1));
        Assertions.assertEquals(0, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
        testVisitor.getBooks(
                new int[] {"Море убитой веры".hashCode(), "Одноглазая ловушка".hashCode()},
                new int[] {17, 23});
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(17).get(0));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(23).get(1));
        Assertions.assertEquals(2, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(2, LibraryStorage.getGivenBooks().size());

    }

    @Test
    void giveBookByBookTest() {

        testVisitor.getBooks(
                new String[] {"Страницы каждой жизни", "Одноглазая ловушка"},
                new int[] {10, 23});

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(23).get(1));
        Assertions.assertEquals(2, LibraryStorage.getGivenBooks().size());
        Assertions.assertEquals(2, testVisitor.getTakenBooks().size());
        testVisitor.giveBook(testVisitor.getTakenBooks().get(1));
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(23).get(1));
        Assertions.assertEquals(1, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(1, LibraryStorage.getGivenBooks().size());

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(10).get(1));
        Assertions.assertEquals(1, LibraryStorage.getGivenBooks().size());
        Assertions.assertEquals(1, testVisitor.getTakenBooks().size());
        testVisitor.giveBook(testVisitor.getTakenBooks().get(0));
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(10).get(1));
        Assertions.assertEquals(0, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
    }

    @Test
    void giveBookByIndexTest() {

        testVisitor.getBooks(
                new String[] {"Страницы каждой жизни", "Море убитой веры"},
                new int[] {10, 17});

        Assertions.assertEquals(2, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(2, LibraryStorage.getGivenBooks().size());
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(17).get(0));
        testVisitor.giveBook(1);
        Assertions.assertEquals(1, testVisitor.getTakenBooks().size());
        Assertions.assertEquals(1, LibraryStorage.getGivenBooks().size());
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(17).get(0));

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(10).get(1));
        testVisitor.giveBook(0);
        Assertions.assertNotNull(LibraryStorage.getAvailableBooks().get(10).get(1));
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
        Assertions.assertEquals(0, testVisitor.getTakenBooks().size());

    }
}