package ru.mail.polis.open.task6.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.people.Librarian;
import ru.mail.polis.open.task6.implementation.people.Manager;
import ru.mail.polis.open.task6.implementation.people.Person;

class LibraryTest {

    private static BookShelf bookShelf;
    private static Manager manager;
    private static Librarian librarian;
    private static Library library;

    @BeforeAll
    static void createLibrary() {
        bookShelf = new BookShelf();
        manager = new Manager(new Person("first1", "last1"));
        librarian = new Librarian(new Person("first2", "last2"));
        library = new Library(bookShelf, manager, librarian);
    }

    @Test
    void getManager() {
        Assertions.assertEquals(manager, library.getManager());
    }

    @Test
    void getLibrarian() {
        Assertions.assertEquals(librarian, library.getLibrarian());
    }

    @Test
    void getBookProvider() {
        Assertions.assertEquals(bookShelf, library.getBookProvider());
    }

    @Test
    void getBookStorage() {
        Assertions.assertEquals(bookShelf, library.getBookStorage());
    }


    @Test
    void open_WorksCorrectly() {
        if (library.isOpened()) {
            library.close();
        }

        library.open();

        Assertions.assertTrue(library.isOpened());
    }

    @Test
    void open_ThrowsOnAlreadyOpened() {

        if (library.isOpened()) {
            library.close();
        }

        library.open();

        Assertions.assertTrue(library.isOpened());

        Assertions.assertThrows(IllegalStateException.class, () -> library.open());
    }

    @Test
    void close_WorksCorrectly() {

        if (!library.isOpened()) {
            library.open();
        }

        library.close();
    }

    @Test
    void close_ThrowsOnAlreadyClosed() {

        if (!library.isOpened()) {
            library.open();
        }

        library.close();

        Assertions.assertFalse(library.isOpened());

        Assertions.assertThrows(IllegalStateException.class, () -> library.close());
    }

}