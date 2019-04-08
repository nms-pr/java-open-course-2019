package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibrarianTest {

    private Visitor visitor = new Visitor("firstName", "secondName");

    @BeforeEach
    void refreshStore() {
        LibraryStorage.getGivenBooks().clear();
        LibraryStorage.getAvailableBooks().clear();

        LibraryStorage.addNewBook(new Book("book 1", 5));
        LibraryStorage.addNewBook(new Book("book 2", 5));
        LibraryStorage.addNewBook(new Book("book 3", 6));
        LibraryStorage.addNewBook(new Book("book 4", 7));

    }

    @Test
    void giveBookByIdTest() {

        int[] requestedIds = {"book 3".hashCode(), "book 1".hashCode()};
        int[] requestedSections = {6, 5};
        Librarian.giveBooks(requestedIds, requestedSections, visitor);

        Book firstGivenBook = new Book("book 3", 6);
        firstGivenBook.setShelfSpace(0);
        Book secondGivenBook = new Book("book 1", 5);
        secondGivenBook.setShelfSpace(0);

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(6).get(0));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(5).get(0));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(firstGivenBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(secondGivenBook));
    }

    @Test
    void giveBookByNameTest() {
        String[] requestedNames = {"book 2", "book 4"};
        int[] requestedSections = {5, 7};
        Librarian.giveBooks(requestedNames, requestedSections, visitor);

        Book firstGivenBook = new Book("book 2", 5);
        firstGivenBook.setShelfSpace(1);
        Book secondGivenBook = new Book("book 4", 7);
        secondGivenBook.setShelfSpace(0);

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(5).get(1));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(7).get(0));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(firstGivenBook));
        Assertions.assertTrue(LibraryStorage.getGivenBooks().contains(secondGivenBook));
    }

    @Test
    void receiveBookTest() {
        Library.open();
        LibraryStorage.giveOutBook(new Book("book 2", 5), visitor);

        Book receivingBook = new Book("book 2", 5);
        receivingBook.setShelfSpace(1);
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(5).get(1));
        Librarian.receiveBook(receivingBook, visitor);
        Assertions.assertEquals(receivingBook, LibraryStorage.getAvailableBooks().get(5).get(1));
    }

}
