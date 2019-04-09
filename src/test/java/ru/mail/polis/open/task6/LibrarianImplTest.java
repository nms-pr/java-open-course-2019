package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

class LibrarianImplTest {

    private Library library = new Library("SPB");
    private Librarian librarian = new LibrarianImpl(library);

    @Test
    void addBook() {
        Manager manager = new ManagerImpl(library);
        manager.bringBook(BookTest.b1);
        manager.bringBook(BookTest.b3);
        library.getBooksAvailable().remove(BookTest.b3);

        List<Book> books = new ArrayList<>(library.getBooksAvailable());
        assertEquals(books, library.getBooksAvailable());

        librarian.addBook(BookTest.b3);
        assertNotEquals(books, library.getBooksAvailable());

        books.add(BookTest.b3);
        assertEquals(books, library.getBooksAvailable());

        assertFalse(librarian.addBook(BookTest.b4));
    }

    @Test
    void issueBook() {
        Manager manager = new ManagerImpl(library);
        manager.bringBook(BookTest.b1);
        manager.bringBook(BookTest.b3);

        List<Book> books = new ArrayList<>(library.getBooksAvailable());
        assertEquals(books, library.getBooksAvailable());

        librarian.issueBook(BookTest.b3);
        assertNotEquals(books, library.getBooksAvailable());

        books.remove(BookTest.b3);
        assertEquals(books, library.getBooksAvailable());

        assertNull(librarian.issueBook(BookTest.b4));


    }
}