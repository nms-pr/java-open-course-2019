package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.narnia1;

public class TestAddOneBook {
    @Test
    void testAddOneBook() {
        LibraryClient samClient = new LibraryClient("Sam");
        assertThrows(IllegalCallerException.class, () -> samClient.addOneBookToCollection(narnia1,
                new ArrayList<Book>()));
    }
}
