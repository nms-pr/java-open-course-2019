package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.mail.polis.open.task6.UtilTest.samClient;
import static ru.mail.polis.open.task6.UtilTest.jackManager;
import static ru.mail.polis.open.task6.UtilTest.paulManager;
import static ru.mail.polis.open.task6.UtilTest.dannyClient;

public class TestLibraryOpenClosed {
    @Test
    void testLibraryOpenClosed() {
        assertThrows(IllegalStateException.class, () -> samClient.askLibrarian());
        jackManager.openLibrary();
        assertDoesNotThrow(() -> samClient.askLibrarian());
        paulManager.closeLibrary();
        assertThrows(IllegalStateException.class, () -> dannyClient.askLibrarian());
    }
}
