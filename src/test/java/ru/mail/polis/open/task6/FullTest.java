package ru.mail.polis.open.task6;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryOneDayTest {

    private static Book book = new Book(17, 4, "Преступление и наказание", "Роман");
    private static Book book1 = new Book(3, 10, "Мастер и Маргарита", "Роман");
    private static Book book2 = new Book(1, 8, "Красная таблетка", "Психология");
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
        
    }
}
