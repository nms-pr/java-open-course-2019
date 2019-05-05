package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ManagerImplTest {
    private static Book book;
    private static ManagerImpl manager;

    @BeforeAll
    static void openTheLibrary() {
        manager = new ManagerImpl("David", "Smith", "Peter", (byte) 40);
        book = new Book(7, 2, "Пламя", "Детектив");
        manager.openLibrary();
        assertEquals(true, Library.getStatus());
    }

    @Test
    void add() {
        assertThrows(IllegalArgumentException.class, () -> manager.add(null));
        manager.add(book);
        assertEquals(true, Library.getBooks().contains(book));
    }

    @Test
    void remove() {
        assertThrows(IllegalArgumentException.class, () -> manager.remove(null));
        Book book1 = Library.getBooks().get(2);
        manager.remove(book1);
        assertEquals(false, Library.getBooks().contains(book1));
    }

    @Test
    void checkStatusAfterOpening() {
        assertEquals(true, Library.getStatus());
    }

    @AfterAll
    static void closeTheLibrary() {
        manager.closeLibrary();
    }
}