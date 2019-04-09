package ru.mail.polis.open.task6.office;

import org.junit.jupiter.api.Test;
import ru.mail.polis.open.task6.Genres;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void getId() {
        Book book = new Book(12, "123", Genres.DRAMA);
        assertEquals(12, book.getId());
    }

    @Test
    void getName() {
        Book book = new Book(12, "123", Genres.DRAMA);
        assertEquals("123", book.getName());
    }

    @Test
    void getGenre() {
        Book book = new Book(12, "123", Genres.DRAMA);
        assertEquals(Genres.DRAMA, book.getGenre());
    }

    @Test
    void equals1() {
        Book book1 = new Book("123", Genres.DRAMA);
        Book book2 = new Book("123", Genres.DRAMA);
        assertEquals(book1, book2);
        Book book3 = new Book(12, "123", Genres.DRAMA);
        Book book4 = new Book(15, "123", Genres.DRAMA);
        assertEquals(book3, book4);
    }

    @Test
    void hashCode1() {
        Book book1 = new Book("123", Genres.DRAMA);
        Book book2 = new Book("123", Genres.DRAMA);
        assertEquals(book1.hashCode(), book2.hashCode());
        Book book3 = new Book(12, "123", Genres.DRAMA);
        Book book4 = new Book(15, "123", Genres.DRAMA);
        assertEquals(book3.hashCode(), book4.hashCode());
    }
}