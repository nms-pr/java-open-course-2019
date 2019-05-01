package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.mail.polis.open.task6.UtilTest.paulManager;
import static ru.mail.polis.open.task6.UtilTest.peterClient;
import static ru.mail.polis.open.task6.UtilTest.dannyClient;
import static ru.mail.polis.open.task6.UtilTest.maxClient;
import static ru.mail.polis.open.task6.UtilTest.samClient;

public class TestNoFreeLibrarians {
    @Test
    void testLibrarians() {
        paulManager.openLibrary();
        assertEquals("Peter", peterClient.askLibrarian());
        assertEquals("Jack", dannyClient.askLibrarian());
        assertEquals("Anna", samClient.askLibrarian());
        assertNull(maxClient.askLibrarian());
    }
}
