package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.dannyClient;
import static ru.mail.polis.open.task6.UtilTest.jackLibrarian;
import static ru.mail.polis.open.task6.UtilTest.paulManager;

public class TestLibrarianSabotage {
    @Test
    void testCheatingLibrarian() {
        paulManager.openLibrary();
        dannyClient.askLibrarian();
        assertThrows(IllegalCallerException.class, () -> jackLibrarian.becomeFree());
    }
}
