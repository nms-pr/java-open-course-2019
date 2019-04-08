package ru.mail.polis.open.task6;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.BookInfo.TakerInfo;

class SimpleLibraryTest {

    SimpleLibrary library;
    Librarian librarian;
    Visitor visitor1;
    Visitor visitor2;
    List<Book> books;

    @BeforeEach
    void beforeEachFunction() {
        library = new SimpleLibrary(6);
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
        try {
            library.addToLibrary(books);
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testSimpleLibrary() {
        library = new SimpleLibrary(3);
        try {
            library.addToLibrary(new Book("t1", "g2", 1));
            library.addToLibrary(new Book("t2", "g2", 2));
            library.addToLibrary(new Book("t3", "g2", 3));
        } catch (LibraryException e) {
            fail(e);
        }
        assertThrows(LibraryException.class, () -> library.addToLibrary(new Book("t4", "g1", 4)));
    }

    @Test
    void testTakeBook() {
        try {
            visitor1.takeBookOut("t1");
            visitor2.takeBookOut("t2");
            books.remove(1);
            books.remove(0);
            assertTrue(ActionTest.listEqualsIgnoreOrder(library.getAvailableBooks(), books));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testReturnBook() {
        try {
            visitor1.takeBookOut("t1");
            visitor2.takeBookOut("t2");
            visitor2.giveBooksBack();
            visitor1.giveBooksBack();
            assertTrue(ActionTest.listEqualsIgnoreOrder(library.getAvailableBooks(), books));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testRemove() {
        try {
            library.remove(books.get(0));
            library.remove(books.get(4));
            books.remove(4);
            books.remove(0);
            assertTrue(ActionTest.listEqualsIgnoreOrder(library.getAvailableBooks(), books));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testClose() {
        library.close();
        assertThrows(LibraryException.class, () -> visitor1.takeBookOut("t1"));
    }

    @Test
    void testOpen() {
        library.close();
        library.open();
        assertDoesNotThrow(() -> visitor1.takeBookOut("t1"));
    }

    @Test
    void testSearchForBook() {
        assertEquals(books.get(0), library.searchForBook("t1"));
        assertEquals(books.get(4), library.searchForBook("t5"));
    }

    @Test
    void testGetTakersHistory() {
        try {
            visitor1.takeBookOut("t1");
            visitor1.giveBooksBack();
            visitor2.takeBookOut("t1");
            visitor2.giveBooksBack();
            Book book = library.searchForBook("t1");
            List<TakerInfo> takersHistory = library.getTakersHistory(book);
            assertEquals(2, takersHistory.size());
            assertEquals(visitor1.getCredentials(), takersHistory.get(0).getTakerCredentials());
            assertEquals(visitor2.getCredentials(), takersHistory.get(1).getTakerCredentials());
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testGetCurrentTakers() {
        try {
            visitor1.takeBookOut("t1");
            visitor2.takeBookOut("t2");

            List<Visitor> expectedVisitors = new ArrayList<Visitor>(2);
            expectedVisitors.add(visitor1);
            expectedVisitors.add(visitor2);

            List<Visitor> visitors = library.getCurrentTakers();
            assertTrue(ActionTest.listEqualsIgnoreOrder(visitors, expectedVisitors));
        } catch (LibraryException e) {
            fail(e);
        }
    }
}
