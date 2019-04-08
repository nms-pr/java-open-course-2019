package ru.mail.polis.open.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActionTest {
    SimpleLibrary library;
    Manager manager;
    Librarian librarian;
    Visitor visitor1;
    Visitor visitor2;
    List<Book> books;

    @BeforeEach
    void beforeEachFunction() {
        library = new SimpleLibrary(6);
        manager = new SimpleManager(library);
        librarian = new SimpleLibrarian(library);
        visitor1 = new SimpleVisitor("Brian", librarian);
        visitor2 = new SimpleVisitor("Stewie", librarian);
        books = new ArrayList<Book>(6);
        books.add(new Book("t1", "g1", 1));
        books.add(new Book("t2", "g1", 2));
        books.add(new Book("t3", "g1", 3));
        books.add(new Book("t4", "g2", 4));
        books.add(new Book("t5", "g2", 4));
        books.add(new Book("t5", "g2", 5));
        manager.addBooks(books);
    }

    @Test
    void takeBookAndGiveItBack() {
        try {
            assertEquals(books, library.getAvailableBooks());

            List<Book> temp = new ArrayList<Book>(books);

            visitor1.takeBookOut("t5");
            temp.remove(4);
            assertEquals(temp, library.getAvailableBooks());
            visitor2.takeBookOut("t1");
            temp.remove(0);
            assertEquals(temp, library.getAvailableBooks());

            visitor1.giveBooksBack();
            visitor2.giveBooksBack();

            assertTrue(listEqualsIgnoreOrder(books, library.getAvailableBooks()));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void takeBookAndNotify() {
        try {
            visitor1.takeBookOut("t1");
            visitor2.takeBookOut("t5");

            List<Book> temp = new ArrayList<Book>(books);
            temp.remove(4);
            temp.remove(0);
            assertEquals(temp, library.getAvailableBooks());

            librarian.notifyTakers();

            assertTrue(listEqualsIgnoreOrder(books, library.getAvailableBooks()));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void closedLibrary() {
        manager.closeLibrary();
        assertThrows(LibraryException.class, () -> visitor1.takeBookOut("t1"));
        assertThrows(LibraryException.class, () -> visitor2.takeBookOut("t2"));
        assertThrows(LibraryException.class, () -> librarian.giveOut("t1", visitor1));
    }

    static boolean listEqualsIgnoreOrder(List<?> l1, List<?> l2) {
        return l1.containsAll(l2) && l2.containsAll(l1);
    }
}
