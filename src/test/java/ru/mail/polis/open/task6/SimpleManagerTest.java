package ru.mail.polis.open.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleManagerTest {

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
    }

    @Test
    void testAddBook() {
        books.add(new Book("t1", "g1", 1));
        books.add(new Book("t2", "g1", 2));
        books.add(new Book("t3", "g1", 3));
        books.add(new Book("t4", "g2", 4));
        books.add(new Book("t5", "g2", 4));
        books.add(new Book("t5", "g2", 5));
        for (Book book : books) {
            manager.addBook(book);
        }
        assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
    }

    @Test
    void testRemoveBook() {
        books.add(new Book("t1", "g1", 1));
        books.add(new Book("t2", "g1", 2));
        books.add(new Book("t3", "g1", 3));
        books.add(new Book("t4", "g2", 4));
        books.add(new Book("t5", "g2", 4));
        books.add(new Book("t5", "g2", 5));
        for (Book book : books) {
            manager.addBook(book);
        }
        manager.removeBook(books.get(3));
        manager.removeBook(books.get(4));
        books.remove(4);
        books.remove(3);
        assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
    }

    @Test
    void testCloseLibrary() {
        manager.closeLibrary();
        manager.addBook(new Book("t1", "g1", 1));
        assertEquals(0, library.getAvailableBooks().size());
    }

    @Test
    void testOpenLibrary() {
        manager.closeLibrary();
        manager.addBook(new Book("t1", "g1", 1));
        manager.openLibrary();
        books.add(new Book("t2", "g1", 2));
        books.add(new Book("t3", "g1", 3));
        for (Book book : books) {
            manager.addBook(book);
        }
        assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
    }

    @Test
    void testAddBooks() {
        books.add(new Book("t1", "g1", 1));
        books.add(new Book("t2", "g1", 2));
        books.add(new Book("t3", "g1", 3));
        books.add(new Book("t4", "g2", 4));
        books.add(new Book("t5", "g2", 4));
        books.add(new Book("t5", "g2", 5));
        manager.addBooks(books);
        assertTrue(ActionTest.listEqualsIgnoreOrder(books, library.getAvailableBooks()));
    }

}
