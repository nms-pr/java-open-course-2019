package ru.mail.polis.open.task6;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleLibrarianTest {
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
    void testGiveOut() {
        try {
            librarian.giveOut("t1", visitor1);
            librarian.giveOut("t2", visitor1);
            librarian.giveOut("t5", visitor2);
            books.remove(4);
            books.remove(1);
            books.remove(0);
            assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testTakeBack() {
        try {
            librarian.giveOut("t1", visitor1);
            librarian.giveOut("t2", visitor1);
            librarian.giveOut("t5", visitor2);
            librarian.takeBack(books.get(0));
            librarian.takeBack(books.get(1));
            librarian.takeBack(books.get(4));
            assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
        } catch (LibraryException e) {
            fail(e);
        }
    }

    @Test
    void testNotifyTakers() {
        try {
            visitor1.takeBookOut("t1");
            visitor2.takeBookOut("t2");
            librarian.notifyTakers();
            assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
        } catch (LibraryException e) {
            fail(e);
        }
    }
}
