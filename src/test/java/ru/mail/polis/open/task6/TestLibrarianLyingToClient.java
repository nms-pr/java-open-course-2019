package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.jackLibrarian;
import static ru.mail.polis.open.task6.UtilTest.physics;
import static ru.mail.polis.open.task6.UtilTest.samClient;

public class TestLibrarianLyingToClient {
    @Test
    void testCheatingLibrarian() {
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.getBookBackFromClient(physics, samClient));
    }
}
