package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShelfTest {

    @Test
    void testWorkingEquals() {
        Shelf shelf1 = new Shelf(5, 1);
        Shelf shelf2 = new Shelf(5, 1);
        assertEquals(shelf1, shelf2);
    }
}
