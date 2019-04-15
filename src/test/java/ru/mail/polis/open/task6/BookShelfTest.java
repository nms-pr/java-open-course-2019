package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookShelfTest {

    @Test
    void getBookShelf() {
        BookShelf bookShelf = new BookShelf(1);
        assertEquals(bookShelf.getNumberBookShelf(), 1);
    }
}
