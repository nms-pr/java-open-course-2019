package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.narnia1;
import static ru.mail.polis.open.task6.UtilTest.samClient;

public class TestAddOneBook {
    @Test
    void testAddOneBook() {
        assertThrows(IllegalCallerException.class, () -> samClient.addOneBookToCollection(narnia1,
                new ArrayList<Book>()));
    }
}
