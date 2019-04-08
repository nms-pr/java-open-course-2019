package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    private Manager manager;

    @BeforeEach
    void refreshStore() {
        manager = new Manager();
        LibraryStorage.getGivenBooks().clear();
        LibraryStorage.getAvailableBooks().clear();
    }

    @Test
    void addBookTest() {

        manager.addBook(new Book("new book 1", 1));
        manager.addBook(new Book("new book 2", 1));
        manager.addBook(new Book("new book 3", 2));

        manager.addBook("new book 4", 3);
        manager.addBook("new book 5", 4);

        Assertions.assertEquals(4, LibraryStorage.getAvailableBooks().size());
        Assertions.assertEquals(0, LibraryStorage.getGivenBooks().size());
        Assertions.assertEquals(2, LibraryStorage.getAvailableBooks().get(1).size());
        Assertions.assertEquals(1, LibraryStorage.getAvailableBooks().get(2).size());
        Assertions.assertEquals(1, LibraryStorage.getAvailableBooks().get(3).size());
        Assertions.assertEquals(1, LibraryStorage.getAvailableBooks().get(4).size());
    }

    @Test
    void removeBookTest() {
        manager.addBook(new Book("new book 1", 1));
        manager.addBook(new Book("new book 2", 1));
        manager.addBook(new Book("new book 3", 2));
        manager.addBook("new book 4", 3);
        manager.addBook("new book 5", 4);

        Book removingBook = new Book("new book 2", 1);
        removingBook.setShelfSpace(1);
        manager.removeBook(removingBook);
        manager.removeBook("new book 3".hashCode(), 2);
        manager.removeBook("new book 5", 4);

        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(1).get(1));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(2).get(0));
        Assertions.assertNull(LibraryStorage.getAvailableBooks().get(4).get(0));

    }

    @Test
    void openCloseTest() {
        manager.openLibrary();
        Assertions.assertFalse(Library.isClosed());

        manager.closeLibrary();
        Assertions.assertTrue(Library.isClosed());
    }

}
