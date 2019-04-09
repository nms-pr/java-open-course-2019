package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class VisitorTest {

    protected static Visitor slava = new Visitor("Slava");
    protected static Visitor kostya = new Visitor("Kostya");

    @Test
    void takesBook() {
        slava.takesBook(BookTest.b1);

        List<Object> books = new ArrayList<>();
        books.add(BookTest.b1);
        assertEquals(books, slava.getBooksOnHand());

        slava.takesBook(BookTest.b2);
        assertNotEquals(books, slava.getBooksOnHand());

        books.add(BookTest.b2);
        assertEquals(books, slava.getBooksOnHand());
    }

    @Test
    void givesBooks() {
        Book[] books = {BookTest.b1, BookTest.b2, BookTest.b3, BookTest.b4};
        kostya.takesBook(books);
        List<Book> booksList = new ArrayList<>(Arrays.asList(books));
        assertEquals(booksList, kostya.getBooksOnHand());

        assertTrue(kostya.givesBooks(books[3]));
        assertFalse(kostya.givesBooks(books[3]));
        assertTrue(kostya.givesBooks(books[0]));

        booksList.remove(books[0]);
        booksList.remove(books[3]);
        assertEquals(booksList, kostya.getBooksOnHand());
    }
}