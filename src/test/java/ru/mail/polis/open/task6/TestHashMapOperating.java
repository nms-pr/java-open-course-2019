package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.winnie2;

public class TestHashMapOperating {
    @Test
    void testHasMapOperating() {
        Librarian jackLibrarian = new Librarian("Jack");
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.bookHashMapOperating(new ManagingPerson.Store(),
                winnie2, 13));
    }
}
