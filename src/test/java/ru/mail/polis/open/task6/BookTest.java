package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookTest {

    @Test
    void getId() {
        Book book1 = new Book(1, 2, "Карта дней", "Фантастика");
        assertEquals(1000765, book1.getId());
    }

    @Test
    void getName() {
        Book book1 = new Book(1, 2, "Карта дней", "Фантастика");
        assertEquals("Карта дней", book1.getName());
    }

    @Test
    void getSection() {
        Book book1 = new Book(1, 2, "Карта дней", "Фантастика");
        assertEquals(book1.getSection(), "Фантастика");
    }
}