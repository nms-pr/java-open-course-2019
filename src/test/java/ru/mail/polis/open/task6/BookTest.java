package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    void testWorkingEquals() {
        Book book1 = new Book(
            "Атлант расправил плечи",
            "Рэнд Айн",
            "Проза",
            1394,
            1,
            1,
            2
        );
        Book book2 = new Book(
            "Атлант расправил плечи",
            "Рэнд Айн",
            "Проза",
            1394,
            5,
            2,
            7
        );
        assertEquals(book1, book2);
    }
}
