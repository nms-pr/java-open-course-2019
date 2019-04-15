package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FullTest {

    private static Book book1 = new Book(1, 8, "Красная таблетка",
        "Психология");
    private static Book newBook = new Book(3, 1, "Финансист",
        "Роман");
    private static Visitor visitor = new Visitor("Алексей", "Лебедев", 23);

    @BeforeAll
    static void openTheLibrary() {
        Library.manager.openTheLibrary();
        assertEquals(true, Library.getOpenOrClosed());
    }

    @Test
    void baseVisitorsAndBooksTest() {
        assertEquals(3, Library.getBookList().size());
        Library.librarian.giveBook("Мастер и Маргарита", visitor);
        assertEquals(true, Library.visitor(visitor));
        assertEquals(2, Library.getBookList().size());
        assertEquals(1, Library.getBusyBooks().size());
        assertThrows(IllegalArgumentException.class, () -> Library.librarian.giveBook("Мастер и Маргарита",
            Library.getVisitorList().get(1)));
        Library.manager.addBook(book1);
        assertEquals(3, Library.getBookList().size());
        Library.librarian.putBook(visitor.getTakenBooks().get(0), visitor);
        assertEquals(4, Library.getBookList().size());
        assertEquals(0, Library.getBusyBooks().size());
        assertThrows(IllegalArgumentException.class, () -> Library.librarian.findBook("Финансист"));
        Library.manager.addBook(newBook);
        assertEquals(newBook, Library.librarian.findBook("Финансист"));
        Library.manager.closeTheLibrary();
        assertThrows(IllegalArgumentException.class, () -> Library.librarian.giveBook("Финанситс",
            Library.getVisitorList().get(1)));
    }

    @AfterAll
    static void clean() {
        Library.setOpenOrClosed(false);
        Library.setCounter();
        Library.getBusyBooks().clear();
        Library.getBookList().clear();
        Library.getBookShelfList().clear();
        Library.getVisitorList().clear();
    }
}
