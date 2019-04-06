package ru.mail.polis.open.task6.implementation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.implementation.people.Librarian;
import ru.mail.polis.open.task6.implementation.people.Manager;
import ru.mail.polis.open.task6.implementation.people.Person;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private static BookShelf bookShelf;
    private static Manager manager;
    private static Librarian librarian;
    private static Library library;

    @BeforeAll
    static void createLibrary() {
        bookShelf = new BookShelf();
        manager = new Manager(new Person("first", "last"), library);
        librarian = new Librarian();
        library = new Library(bookShelf, manager, librarian);
    }

    @Test
    void getManager() {
        assertEquals(manager, library.getManager());
    }

    @Test
    void getLibrarian() {
        assertEquals(librarian, library.getLibrarian());
    }

    @Test
    void getBookProvider() {
        assertEquals(bookShelf, library.getBookProvider());
    }

    @Test
    void getBookStorage() {
        assertEquals(bookShelf, library.getBookStorage());
    }


    @Test
    void open_WorksCorrectly() {
        if (library.isOpened()) {
            library.close();
        }

        library.open();

        assertTrue(library.isOpened());
    }

    @Test
    void open_ThrowsOnAlreadyOpened() {

        if (library.isOpened()) {
            library.close();
        }

        library.open();

        assertTrue(library.isOpened());

        assertThrows(IllegalStateException.class, ()-> library.open());
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

        assertFalse(library.isOpened());

        assertThrows(IllegalStateException.class, ()-> library.close());
    }

}