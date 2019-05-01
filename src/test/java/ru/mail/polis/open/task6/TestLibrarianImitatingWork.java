package ru.mail.polis.open.task6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestLibrarianImitatingWork {
    @Test
    void testCheatingLibrarian() {
        assertThrows(IllegalCallerException.class, Librarian::getAFreeLibrarian);
    }
}
