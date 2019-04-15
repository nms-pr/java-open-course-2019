package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    void getId() {
        Book book = new Book(17, 4, "Преступление и наказание", "Роман");
        assertEquals(30160, book.getId());
    }

    @Test
    void getTitle() {
        Book book = new Book(17, 4, "Преступление и наказание", "Роман");
        assertEquals("Преступление и наказание", book.getBookTitle());
    }

    @Test
    void getGenre() {
        Book book = new Book(17, 4, "Преступление и наказание", "Роман");
        assertEquals("Роман", book.getBookGenre());
    }
}
