package ru.mail.polis.open.task6;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {

    private static Librarian librarian;
    private static Visitor visitor;

    @BeforeAll
    static void beforeStart() {
        librarian = new Librarian("Геннадий", "Букин", 43);
        Library.baseOfVisitorsAndBooks();
    }

    @Test
    void openTheLibraryTest() {
        Library.manager.openTheLibrary();
        assertTrue(Library.getOpenOrClosed());
    }

    @Test
    void visitorTest() {
        Library.visitor(new Visitor("Михаил", "Пореченков", 65));
        assertEquals(6, Library.getVisitorList().size());
    }

    @AfterAll
    static void end() {
        Library.setOpenOrClosed(false);
        Library.setCounter();
        Library.getVisitorList().clear();
        Library.getBookShelfList().clear();
        Library.getBookList().clear();
        Library.getBusyBooks().clear();
    }
}
