package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ManagerTest {

    private static Book book;
    private static Manager manager;

    @BeforeAll
    static void openTheLibrary() {
        manager = new Manager("Геннадий", "Букин", 43);
        book = new Book(1, 8, "Красная таблетка", "Психология");
        manager.openTheLibrary();
        assertEquals(true, Library.getOpenOrClosed());
    }

    @Test
    void addBookTest() {
        assertThrows(IllegalArgumentException.class, () -> manager.addBook(null));
        manager.addBook(book);
        assertEquals(true, Library.getBookList().contains(book));
    }

    @Test
    void deleteBookTest() {
        assertThrows(IllegalArgumentException.class, () -> manager.deleteBook(null));
        Book book2 = Library.getBookList().get(2);
        manager.deleteBook(book2);
        assertEquals(false, Library.getBookList().contains(book2));
    }

    @AfterAll
    static void closeTheLibrary() {
        manager.closeTheLibrary();
    }
}


