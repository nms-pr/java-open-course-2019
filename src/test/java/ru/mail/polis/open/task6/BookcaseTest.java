package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookcaseTest {

    @Test
    void testWorkingEquals() {
        Bookcase bookcase1 = new Bookcase(1);
        Bookcase bookcase2 = new Bookcase(1);
        assertEquals(bookcase1, bookcase2);
    }
}
