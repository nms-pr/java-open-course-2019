package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShelfTest {

    @Test
    void getShelf() {
        Shelf shelf = new Shelf((byte) 1);
        assertEquals(shelf.getShelf(), 1);
    }
}