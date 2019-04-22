package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SomeLibrarianTest {

    static SomeLibrarian librarian = new SomeLibrarian("Petr", 45, 'M');
    static SomeVisitor visitor = new SomeVisitor("Vasya", 15, 'M');
    static SomeManager manager = new SomeManager("Irina", 25, 'F');
    static Book testBook = new Book("Кристина","Кинг","Триллер",3,1);

    @BeforeAll
    static void init() {
        manager.openLibrary();
    }

    @Test
    void giveOutBookTest() {
        Book book = Library.getAllBooks().get(0);
        assertNull(librarian.giveOutBook("1985", visitor));
        assertThrows(IllegalArgumentException.class, () -> librarian.giveOutBook(null, visitor));
        assertEquals(book, librarian.giveOutBook("451 градус по Фаренгейту", visitor));
    }

    @Test
    void findBookTest() {
        assertEquals(testBook, librarian.findBook("Кристина"));
        assertEquals(null, librarian.findBook("Кристин"));
    }

    @AfterAll
    static void clear() {

    }

}