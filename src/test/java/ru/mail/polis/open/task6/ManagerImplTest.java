package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerImplTest {

    private Library library = new Library("SPB");
    private Manager manager = new ManagerImpl(library);

    @Test
    void bringBook() {
        List<Book> books = new ArrayList<>(library.getBooksAll());
        assertEquals(books, library.getBooksAll());

        manager.bringBook(BookTest.b1);
        assertNotEquals(books, library.getBooksAll());

        books.add(BookTest.b1);
        assertEquals(books, library.getBooksAll());

        manager.bringBook(BookTest.b1);
        assertEquals(books, library.getBooksAll());
    }

    @Test
    void removeBook() {
        manager.bringBook(BookTest.b1);
        manager.bringBook(BookTest.b2);
        manager.bringBook(BookTest.b3);

        List<Book> books = new ArrayList<>(library.getBooksAll());
        assertEquals(books, library.getBooksAll());

        manager.removeBook(BookTest.b1);
        assertNotEquals(books, library.getBooksAll());

        books.remove(BookTest.b1);
        assertEquals(books, library.getBooksAll());
    }

    @Test
    void openInstitution() {
        library.close();
        assertEquals(false, library.isWorking());

        manager.openInstitution();
        assertEquals(true, library.isWorking());
    }

    @Test
    void closeInstitution() {
        library.open();
        assertEquals(true, library.isWorking());

        manager.closeInstitution();
        assertEquals(false, library.isWorking());
    }
}